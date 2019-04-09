package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.DataTable;
import com.slife.entity.OrderDetail;

public interface OrderDetailService  extends IBaseService<OrderDetail>{
	
	/**
	 * 查询订单列表
	 * @return
	 */
	DataTable<OrderDetail> listByPage(DataTable<OrderDetail> dt);
	
	/**
	 * 根据id查询订单
	 * @param id
	 * @return
	 */
	OrderDetail selectById(Long id);
	
	int deleteByOrderId(Long orderId);
}
