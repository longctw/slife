package com.slife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slife.base.dao.CrudDao;
import com.slife.entity.Product;
import com.slife.entity.ProductRel;

public interface ProductDao extends CrudDao<Product> {

	List<Product> selectProductByCategory(@Param("categoryId") Long categoryId);
	
	List<Product> selectPieceByToolsId(@Param("toolId") Long toolId);
	
	List<Product> selectToolsByPieceId(@Param("pieceId") Long pieceId);
	
	int deleteRelByToolId(@Param("toolId") Long toolId);
	
	int deleteRelByPieceId(@Param("pieceId") Long pieceId);
	
	int insertProductRel(ProductRel productRel);
}