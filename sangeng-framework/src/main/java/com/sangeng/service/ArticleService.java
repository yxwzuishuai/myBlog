package com.sangeng.service;

import com.sangeng.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.result.Result;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
public interface ArticleService extends IService<Article> {


    /**
     * 门户网站 - 查询最热门文章
     * @return
     */
    Result hotArticleList();

    /**
     * 查询所有文章或者分类页面文章
     * @return
     */
    Result articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ////查看文章详细信息
    Result getArticleDetail(Long id);
}
