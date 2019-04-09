package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.DataTable;
import com.slife.entity.Order;

public interface OrderService extends IBaseService<Order>{
	
	/**
	 * 查询订单列表
	 * @return
	 */
	DataTable<Order> listByPage(DataTable<Order> dt);
	
	/**
	 * 根据id查询订单
	 * @param id
	 * @return
	 */
	Order selectById(Long id);
}
