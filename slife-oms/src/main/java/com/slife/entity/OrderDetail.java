package com.slife.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.slife.base.entity.DataEntity;

@TableName("t_order_detail")
public class OrderDetail extends DataEntity<OrderDetail>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	/**
	 * 订单号
	 */
	@TableField("order_id")
	private Long orderId;
	
	/***
	 * 产品号
	 */
	@TableField("pro_id")
	private Long proId;
	
	/**
	 * 产品描述信息
	 */
	@TableField("pro_info")
	private String proInfo;
	
	/**
	 * 产品单价
	 */
	private Double price;
	
	/**
	 * 
	 */
	@TableField("require_count")
	private Integer requireCount;
	
	@TableField("produce_count")
	private Integer produceCount;
	
	@TableField("deal_count")
	private Integer dealCount;
	
	/**
	 * 订单明细金额汇总
	 */
	private Double money;
	
	/**
	 * 进度
	 */
	private String schedule;
	
	/**
	 * 备用
	 */
	private String state;
	
	/**
	 * 负责部门
	 */
	private String dept;
	
	/**
	 * 负责人
	 */
	private String owner;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 完成时间
	 */
	@TableField("complete_date")
	private Date completeDate;
	
	/**
	 * 图片
	 */
	private String image;
	
	/**
	 * 附件
	 */
	private String files;
	
	@TableField(exist = false)
	private Order order;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getProInfo() {
		return proInfo;
	}

	public void setProInfo(String proInfo) {
		this.proInfo = proInfo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRequireCount() {
		return requireCount;
	}

	public void setRequireCount(Integer requireCount) {
		this.requireCount = requireCount;
	}

	public Integer getProduceCount() {
		return produceCount;
	}

	public void setProduceCount(Integer produceCount) {
		this.produceCount = produceCount;
	}

	public Integer getDealCount() {
		return dealCount;
	}

	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}

