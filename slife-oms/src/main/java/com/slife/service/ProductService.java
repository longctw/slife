package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.DataTable;
import com.slife.entity.Product;

public interface ProductService extends IBaseService<Product>{
	
	/**
	 * 查询产品列表
	 * @return
	 */
	DataTable<Product> listByPage(DataTable<Product> dt);
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	Product selectById(Long id);
	
	/**
     * 保存类别
     * @param category
     */
    void add(Product product);

    /**
     * 更新类别
     * @param category
     */
    void update(Product product);

    /**
     * 删除类别
     * @param id
     */
    Boolean delete(Long id);

    /**
     * 插入关系
     * @param product
     * @param relIds
     */
	void updateProduct(Product product, Long[] relIds);

	void insertProduct(Product product, Long[] relIds);

}
