package com.slife.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.JsTree;
import com.slife.constant.Global;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.CategoryService;
import com.slife.dao.CategoryDao;
import com.slife.entity.Category;

/**
 * @author xull
 * @date 2018/11/1
 * Describe: category servive
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CategoryServiceImpl  extends BaseService<CategoryDao, Category> implements CategoryService {

	@Override
	public List<JsTree> getCategoryTree() {
		List<Category> categoryList = selectList(Condition.create().orderBy("sort", true));
        return makeTree(categoryList);
	}

	/**
     * 新增分类
     *
     * @param category
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void add(Category category) {
    	insert(category);
        if (Global.TOP_TREE_NODE.equals(category.getParentId())) {
        	category.setPath(category.getId() + ".");
        } else {
        	category.setPath(category.getPath() + category.getId() + ".");
        }
        updateById(category);
	}

    /**
     * 更新分类
     *
     * @param category
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
	public void update(Category category) {
    	updateById(category);
	}

    @Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public Boolean deleteCategory(Long id) {
		Category category = selectById(id);
        Optional.ofNullable(category).orElseThrow(() -> new SlifeException(HttpCodeEnum.NOT_FOUND));

        List<Category> delList = selectList(Condition.create().like("path", category.getPath(), SqlLike.RIGHT));
        List<Long> ids = delList.stream().parallel().map(menu -> menu.getId()).collect(Collectors.toList());
        deleteBatchIds(ids);
        return true;
	}
	
}
