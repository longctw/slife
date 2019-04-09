package com.slife.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.OrderDetailDao;
import com.slife.entity.OrderDetail;
import com.slife.service.OrderDetailService;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class OrderDetailServiceImpl extends BaseService<OrderDetailDao, OrderDetail> implements OrderDetailService {
	
	@Override
	public DataTable<OrderDetail> listByPage(DataTable<OrderDetail> dt) {
		return pageSearch(dt);
	}

	@Override
	public OrderDetail selectById(Long id) {
		OrderDetail detail = super.selectById(id);
		return detail; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteByOrderId(Long orderId) {
		return baseMapper.delete(Condition.create().eq("order_id", orderId));
	}
	
}
