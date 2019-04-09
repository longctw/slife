package com.slife.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.slife.base.entity.ReturnDTO;
import com.slife.service.CategoryService;
import com.slife.entity.Category;
import com.slife.util.ReturnDTOUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
    /**
     * 获取分类详情
     *
     * @param id 分类ID
     */
    @GetMapping(value = "select/{id}")
    @ResponseBody
    public Map selectById(@PathVariable Long id) {
        Map map = new HashMap();
        map.put("category", categoryService.selectById(id));
        return map;
    }

    /**
     * 进入分类管理首页
     */
    @GetMapping(value = "")
    public String list(Model model) {

        model.addAttribute("categoryTree", JSON.toJSONString(categoryService.getCategoryTree()));
        return "/category/list";
    }


    /**
     * 保存资源信息
     *
     * @param sysMenu
     * @param redirectAttributes
     *
     * @return
     */
    @PostMapping(value="insert")
    public String save(@Valid Category category, RedirectAttributes redirectAttributes){
        if (ObjectUtils.isEmpty(category.getId())) {
        	categoryService.add(category);
        }else {
        	categoryService.update(category);
        }

        redirectAttributes.addFlashAttribute("message","保存分类成功");
        return "redirect:/category";
    }


    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping(value="delete/{id}")
    @ResponseBody
    public ReturnDTO delete(@PathVariable("id") Long id){
    	categoryService.deleteCategory(id);
        return ReturnDTOUtil.success();
    }
}
