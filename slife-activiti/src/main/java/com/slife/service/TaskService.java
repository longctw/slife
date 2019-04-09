package com.slife.service;

import com.slife.base.vo.DataTable;
import com.slife.constants.ActivitiConstants;
import com.slife.dto.ActivitiQuery;
import com.slife.dto.TaskDTO;
import com.slife.shiro.SlifeSysUser;
import com.slife.util.StringUtils;
import com.slife.utils.ExtUtils;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author felixu
 * @Date 2018.08.14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    @Autowired
    org.activiti.engine.TaskService actTaskService;

    @Autowired
    RepositoryService repositoryService;
    
    @Autowired
    private ProcessEngine processEngine;//流程引擎	

    /**
     * 待办任务
     * @param title
     * @param category
     * @param page
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Task> getTodoTasks(DataTable dt) {
        String userId = SlifeSysUser.id() + "";
        TaskQuery createTaskQuery = actTaskService.createTaskQuery();
        TaskQuery taskQuery = createTaskQuery.taskCandidateOrAssigned(userId)
                .active().includeProcessVariables().orderByTaskCreateTime().desc();
        String category = (String) dt.getSearchParams().get("category");
        if (StringUtils.isNotBlank(category)) {
            taskQuery.taskCategory(category);
        }
        String title = (String) dt.getSearchParams().get("title");
        if (StringUtils.isNotBlank(title)){
            taskQuery.processVariableValueLikeIgnoreCase("title", "%" + title + "%");
        }
        
        List<Task> listPage = taskQuery.listPage((dt.getPageNumber() - 1) * dt.getPageSize(), dt.getPageSize());
//        Map<String, List<TaskDTO>> collect = listPage
//                .stream()
//                .map(task -> {
//                    TaskDTO dto = getTaskDTO(task, task.getAssignee() == null ?
//                            ActivitiConstants.TaskStatus.TASK_STATUS_CLAIM : ActivitiConstants.TaskStatus.TASK_STATUS_TODO);
//                    dto.setTime(task.getCreateTime().getTime());
//                    return dto;
//                }).collect(Collectors.groupingBy(TaskDTO::getCategory));
        //dt.setRows(listPage);
        return listPage;
    }

    private TaskDTO getTaskDTO(TaskInfo task, String status) {
        ProcessDefinition processDefinition = getProcessDefinition(task.getProcessDefinitionId());
        String deploymentId = processDefinition.getDeploymentId();
        Deployment deployment = getDeployment(deploymentId);
        return ExtUtils.toTaskDTO(task, status, processDefinition, deployment);
    }

    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    }

    private Deployment getDeployment(String deploymentId) {
        return repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    }
    
    /**
	 * 启动业务流程
	 */
	public void startProcess(String pdkey, String buskey) {
		//启动采购业务流程			
		RuntimeService runtimeService = processEngine.getRuntimeService();//运行时服务对象
		runtimeService.startProcessInstanceByKey(pdkey, buskey );
	}
	
	/**
	 * 完成任务
	 */
	public void completeTask(String pdkey, String buskey, Map variables) {
		String userId = SlifeSysUser.id() + "";
		Task task = getTaskBybusKey(pdkey, buskey);//得到任务对象	
		actTaskService.claim(task.getId(), userId);//保存任务执行人ID
		if(variables != null){
			actTaskService.setVariables(task.getId(), variables);
		}
		actTaskService.complete(task.getId());//完成任务
	}
	
	public Task getTaskBybusKey(String pdkey, String buskey){
		TaskQuery taskQuery = actTaskService.createTaskQuery();//任务查询
		taskQuery.processDefinitionKey(pdkey);//查询条件：流程定义KEY
		taskQuery.processInstanceBusinessKey(buskey);//查询条件：业务KEY		
		Task task = taskQuery.singleResult();//得到任务对象
		
		return task;
	}
	
	/**
	 * 历史任务列表
	 * @return
	 */
	public List<HistoricTaskInstance> getHistoricTaskInstanceList(
			ActivitiQuery activitiQuery,int firstResult,int maxResults){
		HistoricTaskInstanceQuery query = createHistoricTaskInstanceQuery(activitiQuery);		
		return query.listPage(firstResult, maxResults);
	}
	
	
	
	/**
	 * 根据条件创建历史任务查询对象
	 * @param activitiQuery
	 * @return
	 */
	private HistoricTaskInstanceQuery createHistoricTaskInstanceQuery(ActivitiQuery activitiQuery){
		HistoryService historyService = processEngine.getHistoryService();
		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();		
		if(activitiQuery!=null){
			if(activitiQuery.getUserId()!=null && activitiQuery.getUserId().length()>0){
				query.taskAssignee(activitiQuery.getUserId());//任务执行人
			}
			if(activitiQuery.getDate1()!=null){
				query.taskCompletedAfter(activitiQuery.getDate1());
			}
			if(activitiQuery.getDate2()!=null){
				query.taskCompletedBefore(activitiQuery.getDate2());
			}			
		}
		return query;
	}
	
	/**
	 * 根据流程定义KEY和任务定义KEY 查询业务KEY集合
	 * @param pdkey 流程定义KEY
	 * @param task_def_key 任务定义KEY 
	 * @return
	 */
	public List<Long> getBusinessKeyList(DataTable dt){		
		List<Task> taskList = getTodoTasks(dt);//获得任务列表 
		List<Long> businessKeyList=new ArrayList<Long>();//业务KEY 集合		
		RuntimeService runtimeService = processEngine.getRuntimeService();//运行时服务对象	
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();//获得流程实例查询		
		for(Task task:taskList){
			String processInstanceId = task.getProcessInstanceId();//得到流程实例ID
			processInstanceQuery.processInstanceId(processInstanceId);//设置条件:流程实例ID
			ProcessInstance processInstance = processInstanceQuery.singleResult();//获得流程实例			
			String businessKey = processInstance.getBusinessKey();//获得业务KEY
			if(businessKey!=null && businessKey.length()>0){
				businessKeyList.add(Long.valueOf(businessKey));//将业务KEY添加到返回列表
			}
		}
		return businessKeyList;
	}
	/**
	 * 根据流程定义KEY和任务定义KEY 查询业务KEY集合
	 * @param pdkey 流程定义KEY
	 * @param task_def_key 任务定义KEY 
	 * @return
	 */
	public List<Long> getHistoryBusinessKeyList(){		
		String userId = SlifeSysUser.id() + "";
		HistoryService historyService = processEngine.getHistoryService();
		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
		HistoricProcessInstanceQuery instanceQuery = historyService.createHistoricProcessInstanceQuery();
		query.taskAssignee(userId);
		List<HistoricTaskInstance> list = query.list();
		
		List<Long> businessKeyList=new ArrayList<Long>();//业务KEY 集合		
		
		for(HistoricTaskInstance instance : list){
			String processInstanceId = instance.getProcessInstanceId();//得到流程实例ID
			instanceQuery.processInstanceId(processInstanceId);
			HistoricProcessInstance processInstance = instanceQuery.singleResult();//获得流程实例			
			String businessKey = processInstance.getBusinessKey();//获得业务KEY
			if(businessKey!=null && businessKey.length()>0){
				businessKeyList.add(Long.valueOf(businessKey));//将业务KEY添加到返回列表
			}
		}
		return businessKeyList;
	}
}
