package com.slife.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.dto.ActivitiQuery;
import com.slife.entity.Order;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.OrderDetailService;
import com.slife.service.OrderService;
import com.slife.service.TaskService;
import com.slife.util.ReturnDTOUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private TaskService taskService;
	
	private static final String PRO_KEY = "oms_order";
	
    @ApiOperation(value = "进入订单管理列表界面", notes = "进入订单管理列表界面")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath() + "/order/");
        return "order/list";
    }
	
	/**
     * 对订单列表分页显示
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取订单列表数据")
    @ApiOperation(value = "获取订单列表数据", notes = "获取订单列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<Order> list(@RequestBody DataTable dt, ServletRequest request) {
        return orderService.listByPage(dt);
    }
    
    /**
     * 进入订单创建界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/insert")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "insert");
        Order order = new Order();
        order.setId(0L);
        model.addAttribute("order", order);
        model.addAttribute("url", request.getContextPath() + "/order/");
        return "order/detail";
    }
    
    /**
     * 更新订单明细界面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
    	model.addAttribute("action", "detail");
    	model.addAttribute("url", request.getContextPath() + "/order/");
    	Order order = orderService.selectById(id);
    	logger.info(JSON.toJSONString(order));
    	model.addAttribute("order", order);
    	return "order/detail";
    }
    
    /**
     * 更新订单界面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "update/{id}")
    public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        model.addAttribute("action", "update");
        model.addAttribute("url", request.getContextPath() + "/order/");
        Order order = orderService.selectById(id);
        logger.info(JSON.toJSONString(order));
        model.addAttribute("order", order);
        return "order/detail";
    }

    @ApiOperation(value = "创建订单操作", notes = "创建订单操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid Order order) {
    	order.setId(null);
    	orderService.insert(order);
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("money", order.getSumMoney());
    	taskService.startProcess(PRO_KEY, order.getId()+"");
    	taskService.completeTask(PRO_KEY, order.getId()+"", variables);
        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "更新订单", notes = "更新订单")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid Order order) {
    	Order orderDB = orderService.selectById(order.getId());
        if (ObjectUtils.isEmpty(orderDB)) {
            throw new SlifeException(HttpCodeEnum.NOT_FOUND);
        }

        order.setId(orderDB.getId());
        order.setCreateDate(orderDB.getCreateDate());
        order.setCreateId(orderDB.getCreateId());
        orderService.updateById(order);
        return ReturnDTOUtil.success();
    }
    
    @SLog("批量删除订单")
    @ApiOperation(value = "批量删除订单", notes = "批量删除订单")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = orderService.deleteBatchIds(ids);
        
        for(Long id : ids){
        	orderDetailService.deleteByOrderId(id);
        }
        
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();
    }
    
    /**
     * 进入订单创建界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/todo")
    public String taskTodo(Model model, HttpServletRequest request) {
        model.addAttribute("preUri", "todo/");
        model.addAttribute("url", request.getContextPath() + "/order/");
        return "order/list";
    }
    
    /**
     * 对待办理订单分页显示
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取待办理订单数据")
    @ApiOperation(value = "获取待办理订单数据", notes = "获取待办理订单:使用约定的DataTable")
    @PostMapping(value = "/todo/list")
    @ResponseBody
    public DataTable<Order> todoList(@RequestBody DataTable dt, ServletRequest request) {
    	List<Long> businessKeyList = null; 
    	
    	Map params = dt.getSearchParams();
    	String status = (String)params.get("processStatus");
    	
    	if ("todo".equals(status)){
    		businessKeyList = taskService.getBusinessKeyList(dt);
    	}else{
    		businessKeyList = taskService.getHistoryBusinessKeyList();
    	}
    	
    	if (businessKeyList == null || businessKeyList.isEmpty()){
    		return dt;
    	}
    	
    	List<Order> orderList = new ArrayList<Order>(); 
    	for(Long id : businessKeyList){
    		Order order = orderService.selectById(id);
    		orderList.add(order);
    	}
    	
    	dt.setRows(orderList);
        return dt;
    }
    /**
     * 对待办理订单分页显示
     * @param dt
     * @param request
     * @return
     */
    @PostMapping(value = "/todo/check/{id}")
    @ResponseBody
    public ReturnDTO check(@PathVariable("id") Long id) {
    	Order orderDB = orderService.selectById(id);
    	
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("money", orderDB.getSumMoney());
    	taskService.completeTask(PRO_KEY, id + "", variables);
    	
    	Task task = taskService.getTaskBybusKey(PRO_KEY, id + "");
    	if (task != null){
    		orderDB.setSchedule(task.getName());
    	}else {
    		orderDB.setSchedule("已结束");
    	}
    	orderService.updateById(orderDB);
    	return ReturnDTOUtil.success();
    }
    /**
     * 对待办理订单分页显示
     * @param dt
     * @param request
     * @return
     */
    @GetMapping(value = "/todo/history")
    @ResponseBody
    public DataTable<Order> history(@RequestBody DataTable dt, ServletRequest request) {
    	List<Long> businessKeyList = taskService.getHistoryBusinessKeyList();
    	List<Order> orderList = new ArrayList<Order>(); 
    	
    	for(Long id : businessKeyList){
    		Order order = orderService.selectById(id);
    		orderList.add(order);
    	}
    	
    	dt.setRows(orderList);
        return dt;
    }

}
