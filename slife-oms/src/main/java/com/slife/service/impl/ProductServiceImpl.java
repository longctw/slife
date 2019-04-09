package com.slife.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.ProductDao;
import com.slife.entity.Category;
import com.slife.entity.Product;
import com.slife.entity.ProductRel;
import com.slife.service.CategoryService;
import com.slife.service.ProductService;
import com.slife.util.StringUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl extends BaseService<ProductDao, Product> implements ProductService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public DataTable<Product> listByPage(DataTable<Product> dt) {
		Map<String, Object> searchParams = dt.getSearchParams();
		String categoryId =  (String) searchParams.get("search_start_category_id");
		
		if (StringUtils.isNotEmpty(categoryId)){
			List<Long> list = childrenCategoryId(categoryId);
			searchParams.put("search_in_category_id", list);
		}
		
		List<Product> rows = pageSearch(dt).getRows();
		
		for(Product product : rows){
			buildRecord(product);
		}
		
		return pageSearch(dt);
	}
	
	@SuppressWarnings("unchecked")
	private List<Long> childrenCategoryId(String categoryId){
		List<Long> result = new ArrayList<Long>();
		List<Category> categoryList = categoryService.selectList(Condition.create().like("path", categoryId + "."));
		
		for(Category category : categoryList){
			result.add(category.getId());
		}
		
		return result;
	}

	@Override
	public void add(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

 	@Override
 	@Transactional(readOnly = false)
	public void updateProduct(Product product, Long[] relIds) {
 		Product proDB = selectById(product.getId());
 		
		// 删除原纪录的关系
		if (proDB.getRelation() == 1){
			productDao.deleteRelByToolId(proDB.getId());
		}else if (proDB.getRelation() == 2){
			productDao.deleteRelByPieceId(proDB.getId());
		}
		
		// 执行更新
		updateById(product);
		
		//添加新的关联关系
		if (product.getRelation() == 1){
			for (Long pieceId : relIds){
				ProductRel pr = new ProductRel();
				pr.setToolId(product.getId());
				pr.setPieceId(pieceId);
				productDao.insertProductRel(pr);
			}
		}else if (product.getRelation() == 2){
			for (Long toolId : relIds){
				ProductRel pr = new ProductRel();
				pr.setToolId(toolId);
				pr.setPieceId(product.getId());
				productDao.insertProductRel(pr);
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void insertProduct(Product product, Long[] relIds) {
		insert(product);
		Integer relation = product.getRelation();
		// 填充数据
		if (relation == 1){
			for (Long pieceId : relIds){
				ProductRel pr = new ProductRel();
				pr.setToolId(product.getId());
				pr.setPieceId(pieceId);
				productDao.insertProductRel(pr);
			}
		}else if (relation == 1){
			for (Long toolId : relIds){
				ProductRel pr = new ProductRel();
				pr.setToolId(toolId);
				pr.setPieceId(product.getId());
				productDao.insertProductRel(pr);
			}
		}
	}

	@Override
	public Product selectById(Long id) {
		Product product = super.selectById(id);
		buildRecord(product);
		return product;
	}

	private void buildRecord(Product product) {
		Long categoryId = product.getCategoryId();
		if (categoryId != null){
			Category category = categoryService.selectById(categoryId);
			product.setCategory(category);
		}
		
		int relation = product.getRelation();
		
		if (relation ==1){
			List<Product> pieceList = productDao.selectPieceByToolsId(product.getId());
			product.setPieceList(pieceList);
		}else{
			List<Product> toolList = productDao.selectToolsByPieceId(product.getId());
			product.setToolList(toolList);
		}
	}
	
}
