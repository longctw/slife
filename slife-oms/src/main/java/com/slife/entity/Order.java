package com.slife.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.slife.base.entity.DataEntity;
import com.slife.util.StringUtils;

@TableName("t_order")
public class Order extends DataEntity<Order>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	/**
	 * 订单标题
	 */
	private String title;
	
	/**
	 * 订单标识，订单号
	 */
	@TableField("order_id")
	private String orderId;
	
	/**
	 * 委托人
	 */
	private String bailor;
	
	/***
	 * 进度
	 */
	private String schedule;
	
	/**
	 * 订单总金额
	 */
	@TableField("sum_money")
	private double sumMoney;
	
	/***
	 * 要求完成时间
	 */
	@TableField("require_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date requireDate;
	
	/***
	 * 实际完成时间
	 */
	@TableField("complete_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date completeDate;
	
	/**
	 * 订单类型
	 */
	private String type;

	/**
	 * ** 备用
	 */
	private String state;
	
	/**
	 * 图片
	 */
	private String image;
	
	/***
	 * 附件
	 */
	private String files;
	
	@TableField("cus_name")
	private String cusName;
	
	@TableField("cus_company")
	private String cusCompany;
	
	@TableField("cus_phone")
	private String cusPhone;
	
	@TableField("cus_address")
	private String cusAddress;
	
	@TableField("cus_desc")
	private String cusDesc;
	
	@TableField("express_info")
	private String expressInfo;
	
	@TableField("express_date")
	private Date expressDate;

	@TableField(exist=false)
	private List<OrderDetail> details;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBailor() {
		return bailor;
	}

	public void setBailor(String bailor) {
		this.bailor = bailor;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusCompany() {
		return cusCompany;
	}

	public void setCusCompany(String cusCompany) {
		this.cusCompany = cusCompany;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusDesc() {
		return cusDesc;
	}

	public void setCusDesc(String cusDesc) {
		this.cusDesc = cusDesc;
	}

	public String getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(String expressInfo) {
		this.expressInfo = expressInfo;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getExpressDate() {
		return expressDate;
	}

	public void setExpressDate(Date expressDate) {
		this.expressDate = expressDate;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}
	
	public List<String> getImages() {
		if (StringUtils.isEmpty(image)){
			return null;
		}
		
		List<String> list = Arrays.asList(image.split(","));
		return list;
	}
}
