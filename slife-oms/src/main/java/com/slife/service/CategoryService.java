package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.JsTree;
import com.slife.entity.Category;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chen
 * @date 2017/9/19
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface CategoryService extends IBaseService<Category> {

    /**
     * 获取类别树
     * @return
     */
     List<JsTree> getCategoryTree();

    /**
     * 保存类别
     * @param category
     */
     void add(Category category);

    /**
     * 更新类别
     * @param category
     */
    void update(Category category);

    /**
     * 删除类别
     * @param id
     */
    Boolean deleteCategory(Long id);
    
}