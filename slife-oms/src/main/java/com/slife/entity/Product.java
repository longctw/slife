package com.slife.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.DataEntity;
import com.slife.util.StringUtils;

@TableName("t_product")
public class Product extends DataEntity<Product>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	/**
	 * 产品编号，用户定义
	 */
	@TableField("pro_no")
	private String proNo;
	
	/**
	 * 产品名称
	 */
	@TableField("pro_name")
	private String proName;
	
	@TableField("pro_util")
	private String proUtil;

	/***
	 * 产品描述
	 */
	private String description;
	
	/**
	 * 产品规格
	 */
	private String specifications;
	
	/**
	 * 所用材料
	 */
	private String material;
	
	/**
	 * 型号
	 */
	private String models;
	
	/**
	 * 图号
	 */
	@TableField("image_no")
	private String imageNo;
	
	/**
	 * 图片URL
	 */
	@TableField("image_url")
	private String imageURL;
	
	/**
	 *  附件URL
	 */
	@TableField("file_url")
	private String fileURL;
	
	/**
	 * 成本价
	 */
	@TableField("in_price")
	private Double inPrice;
	
	/**
	 * 售价
	 */
	@TableField("out_price")
	private Double outPrice;
	
	@TableField("category_id")
	private Long categoryId;
	
	@TableField(exist=false)
    private Category category;
	
	/**
	 * 类型代码
	 */
	private int type;
	/**
	 * 产品关系代码
	 */
	private int relation;
	
	/**
	 * 条形码
	 */
	private String barcode;
	
	@TableField(exist=false)
	private List<Product> pieceList;
    
	@TableField(exist=false)
    private List<Product> toolList;

	@Length(min = 0, max = 20, message = "产品编号长度必须介于 1 和 20 之间")
	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProUtil() {
		return proUtil;
	}

	public void setProUtil(String proUtil) {
		this.proUtil = proUtil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min = 0, max = 500, message = "产品描述信息长度必须介于 1 和 500 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getImageNo() {
		return imageNo;
	}

	public void setImageNo(String imageNo) {
		this.imageNo = imageNo;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public List<Product> getPieceList() {
		return pieceList;
	}

	public void setPieceList(List<Product> pieceList) {
		for (Product piece : pieceList){
			if (piece.getRelation() == 1){
				return ;
			}
		}
		
		if (relation == 1){
			this.pieceList = pieceList;
		}
	}

	public List<Product> getToolList() {
		return toolList;
	}

	public void setToolList(List<Product> toolList) {
		for (Product tool : toolList){
			if (tool.getRelation() == 2){
				return ;
			}
		}
		
		if (relation == 1){
			this.toolList = toolList;
		}
	}
	
	public String getPiece(){
		StringBuffer result = new StringBuffer();
		
		if (pieceList != null && pieceList.size()>0){
			for (int i = 0; i < pieceList.size(); i++){
				result.append(pieceList.get(i).getProName());
				if ((i + 1) != pieceList.size()){
					result.append(",");
				}
			}
		}
		
		return result.toString();
	}
	
	public String getPieceIds(){
		StringBuffer result = new StringBuffer();
		
		if (pieceList != null && pieceList.size()>0){
			for (int i = 0; i < pieceList.size(); i++){
				result.append(pieceList.get(i).getId());
				if ((i + 1) != pieceList.size()){
					result.append(",");
				}
			}
		}
		
		return result.toString();
	}
	
	public String getTool(){
		StringBuffer result = new StringBuffer();
		
		if (toolList != null && toolList.size()>0){
			for (int i = 0; i < toolList.size(); i++){
				result.append(toolList.get(i).getProName());
				if ((i + 1) != toolList.size()){
					result.append(",");
				}
			}
		}
		
		return result.toString();
	}
	public String getToolIds(){
		StringBuffer result = new StringBuffer();
		
		if (toolList != null && toolList.size()>0){
			for (int i = 0; i < toolList.size(); i++){
				result.append(toolList.get(i).getId());
				if ((i + 1) != toolList.size()){
					result.append(",");
				}
			}
		}
		
		return result.toString();
	}
	
	public String getCategoryName(){
		if (category != null){
			return category.getName();
		}
		return "";
	}

	public String getRelIds() {
		if (relation == 1){
			return getPieceIds();
		}else{
			return getToolIds();
		}
	}

	public String getRelNames() {
		if (relation == 1){
			return getPiece();
		}else{
			return getTool();
		}
	}

	public List<String> getImages() {
		if (StringUtils.isEmpty(imageURL)){
			return null;
		}
		
		List<String> list = Arrays.asList(imageURL.split(","));
		return list;
	}
	
}
