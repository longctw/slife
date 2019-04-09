package com.slife.dao;

import org.springframework.stereotype.Component;

import com.slife.base.dao.TreeDao;
import com.slife.entity.Category;

@Component
public interface CategoryDao extends TreeDao<Category> {
	
}