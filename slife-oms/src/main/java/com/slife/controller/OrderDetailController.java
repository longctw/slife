package com.slife.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.slife.entity.OrderDetail;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.OrderDetailService;
import com.slife.util.ReturnDTOUtil;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/od")
public class OrderDetailController extends BaseController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
    @ApiOperation(value = "进入订单明细管理列表界面", notes = "进入订单明细管理列表界面")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath() + "/od/");
        return "od/list";
    }
	
	/**
     * 对订单明细列表分页显示
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取订单明细列表数据")
    @ApiOperation(value = "获取订单明细列表数据", notes = "获取订单明细列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<OrderDetail> list(@RequestBody DataTable dt, ServletRequest request) {
        return orderDetailService.listByPage(dt);
    }
    
    /**
     * 进入订单明细创建界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/insert")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "insert");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(0L);
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("url", request.getContextPath() + "/od/");
        return "od/detail";
    }
    
    /**
     * 更新订单明细界面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "update/{id}")
    public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        model.addAttribute("action", "update");
        model.addAttribute("url", request.getContextPath() + "/od/");
        OrderDetail orderDetail = orderDetailService.selectById(id);
        logger.info(JSON.toJSONString(orderDetail));
        model.addAttribute("orderDetail", orderDetail);
        return "od/detail";
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
    	model.addAttribute("url", request.getContextPath() + "/od/");
    	OrderDetail orderDetail = orderDetailService.selectById(id);
    	logger.info(JSON.toJSONString(orderDetail));
    	model.addAttribute("orderDetail", orderDetail);
    	return "od/detail";
    }

    @ApiOperation(value = "创建订单明细操作", notes = "创建订单明细操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid OrderDetail orderDetail) {
    	orderDetail.setId(null);
    	orderDetailService.insert(orderDetail);
        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "更新订单明细", notes = "更新订单明细")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid OrderDetail orderDetail) {
    	OrderDetail orderDetailDB = orderDetailService.selectById(orderDetail.getId());
        if (ObjectUtils.isEmpty(orderDetailDB)) {
            throw new SlifeException(HttpCodeEnum.NOT_FOUND);
        }

        orderDetail.setId(orderDetailDB.getId());
        orderDetail.setCreateDate(orderDetailDB.getCreateDate());
        orderDetail.setCreateId(orderDetailDB.getCreateId());
        orderDetailService.updateById(orderDetail);
        return ReturnDTOUtil.success();
    }
    
    @SLog("批量删除订单明细")
    @ApiOperation(value = "批量删除订单明细", notes = "批量删除订单明细")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = orderDetailService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();
    }
}
