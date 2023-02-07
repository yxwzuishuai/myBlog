package com.sangeng.controller;


import com.sangeng.result.Result;
import com.sangeng.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @ApiOperation("门户网站 - 查询分类列表")
    @GetMapping("/getCategoryList")
    public Result getCategoryList() {
        return categoryService.getCategoryList();
    }

}

