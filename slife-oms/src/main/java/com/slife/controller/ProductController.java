package com.slife.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.Product;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.CategoryService;
import com.slife.service.ProductService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

    @ApiOperation(value = "进入产品管理列表界面", notes = "进入产品管理列表界面")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
    	model.addAttribute("categoryTree", JSON.toJSONString(categoryService.getCategoryTree()));
        model.addAttribute("url", request.getContextPath() + "/product/");
        return "product/list";
    }
	
	/**
     * 对产品列表分页显示
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取产品列表数据")
    @ApiOperation(value = "获取产品列表数据", notes = "获取产品列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<Product> list(@RequestBody DataTable dt, ServletRequest request) {
        return productService.listByPage(dt);
    }
    
    /**
     * 进入产品创建界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/insert")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "insert");
        Product product = new Product();
        product.setId(0L);
        model.addAttribute("product", product);
        model.addAttribute("cateTree", JSON.toJSONString(categoryService.getCategoryTree()));
        model.addAttribute("url", request.getContextPath() + "/product/");
        return "product/detail";
    }
    
    /**
     * 更新产品界面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "update/{id}")
    public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        model.addAttribute("action", "update");
        model.addAttribute("url", request.getContextPath() + "/product/");
        Product product = productService.selectById(id);
        logger.info(JSON.toJSONString(product));
        model.addAttribute("cateTree", JSON.toJSONString(categoryService.getCategoryTree()));
        model.addAttribute("product", product);
        return "product/detail";
    }

    @ApiOperation(value = "创建产品操作", notes = "创建产品操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid Product product, @RequestParam(value = "relIds", defaultValue = "") Long[] relIds) {
    	product.setId(null);
    	productService.insertProduct(product, relIds);
        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "更新产品", notes = "更新产品")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid Product product, @RequestParam(value = "relIds", defaultValue = "") Long[] relIds) {
    	Product productDB = productService.selectById(product.getId());
        if (ObjectUtils.isEmpty(productDB)) {
            throw new SlifeException(HttpCodeEnum.NOT_FOUND);
        }

        product.setId(productDB.getId());
        product.setCreateDate(productDB.getCreateDate());
        product.setCreateId(productDB.getCreateId());
        productService.updateProduct(product, relIds);
        return ReturnDTOUtil.success();
    }
    
    @SLog("批量删除产品")
    @ApiOperation(value = "批量删除产品", notes = "批量删除产品")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = productService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();
    }
}
