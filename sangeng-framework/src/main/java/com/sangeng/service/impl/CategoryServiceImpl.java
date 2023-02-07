package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.entity.Article;
import com.sangeng.entity.Category;
import com.sangeng.entity.vo.CategoryVo;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.result.Result;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.util.BeanCopyUtils;
import com.sangeng.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    /**
     * 门户网站 - 查询分类列表
     *
     * @return
     */
    @Override
    public Result getCategoryList() {
        // 查询文章表，状态为已发布的文章
        List<Article> articleList = articleService.list(new LambdaQueryWrapper<Article>().eq(Article::getStatus, Constants.ARTICLE_STATUS_NORMAL));
        
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查询分类表
        List<Category> categories = listByIds(categoryIds);

        // 过滤，只要正常状态的分类
        categories = categories.stream()
                .filter(category -> Constants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        // 封装 vo
        final List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return Result.okResult(categoryVos);
    }
}
