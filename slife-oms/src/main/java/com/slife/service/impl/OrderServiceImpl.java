package com.slife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.OrderDao;
import com.slife.entity.Order;
import com.slife.entity.OrderDetail;
import com.slife.service.OrderDetailService;
import com.slife.service.OrderService;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseService<OrderDao, Order> implements OrderService {
	
	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	public DataTable<Order> listByPage(DataTable<Order> dt) {
		return pageSearch(dt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order selectById(Long id) {
		Order order = super.selectById(id);
		List<OrderDetail> details = orderDetailService.selectList(Condition.create().eq("order_id", id));
		order.setDetails(details);
		return order;
	}
	
}
