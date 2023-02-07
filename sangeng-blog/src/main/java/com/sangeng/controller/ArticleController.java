package com.sangeng.controller;


import com.sangeng.entity.Article;
import com.sangeng.result.Result;
import com.sangeng.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("门户网站 - 查询最热门文章")
    @GetMapping("/hotArticleList")
    public Result hotArticleList() {
        //查询热门文章，封装成Result返回
        return articleService.hotArticleList();
    }

    @ApiOperation("在首页和分类页面下查询文章列表")
    @GetMapping("/articleList")
    public Result articleList(Integer pageNum, Integer pageSize, Long categoryId){
        //查询所有文章或者分类文章，封装成Result返回
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public Result getArticleDetail(@PathVariable("id") Long id ){
        //查看文章详细信息
        return articleService.getArticleDetail(id);

    }

}

