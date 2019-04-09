package com.slife.dto;
import java.util.Date;

/**
 * 工作流查询条件封装
 * @author Administrator
 *
 */
public class ActivitiQuery {

	private String userId;	
	private Date date1;
	private Date date2;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	
	
	
}