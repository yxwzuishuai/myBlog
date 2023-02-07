package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.entity.Article;
import com.sangeng.entity.vo.ArticleDetailVo;
import com.sangeng.entity.vo.ArticleListVo;
import com.sangeng.entity.vo.HotArticleVo;
import com.sangeng.entity.vo.PageVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.result.Result;
import com.sangeng.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.service.CategoryService;
import com.sangeng.util.BeanCopyUtils;
import com.sangeng.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private CategoryService categoryService;

    /**
     * 门户网站 - 查询最热门文章
     *
     * @return
     */
    @Override
    public Result hotArticleList() {
         List<Article> articles = baseMapper.selectList(new LambdaQueryWrapper<Article>()
                //正式发布的文章
                .eq(Article::getStatus, Constants.ARTICLE_STATUS_NORMAL)
                //按照viewCount（浏览量）进行排序
                .orderByDesc(Article::getViewCount)
                .last("limit 10"));
        // 使用封装的工具类拷贝对象，只返回需要显示的字段给前端
         List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return Result.okResult(articleVos);
    }

    //查询所有文章或者分类页面文章
    @Override
    public Result articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        //判断category是否为null 并且 防止前端传 -1
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //0表示正常，1表示草稿箱
        queryWrapper.eq(Article::getStatus,Constants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getTop);
        //分页查询到的数据会保存到page中
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);
        /*但是返回的值中要求有 categoryName，现在只有 categoryId，所有在 Article中增加categoryName（非数据库字段）
        通过categoryId查询categoryName后保存到page中
        */
        //分页查询到的所有数据
        List<Article> articles = page.getRecords();
        //articles和page.getRecords()获取到的地址值是一样的
        articles.stream()
                .map(article->
                    //set方法返回值是void ，但是可以在 Article实体类中加Accessors注解，将set方法的返回值改为 Article
                    article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())
                )
                .collect(Collectors.toList());


        //page.getRecords()表示分页查询到的数据
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return Result.okResult(pageVo);
    }

    //查看文章详细信息
    @Override
    public Result getArticleDetail(Long id) {

        Article article = baseMapper.selectById(id);

        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        Long categoryId = articleDetailVo.getCategoryId();

        articleDetailVo.setCategoryName(categoryService.getById(categoryId).getName());

        return Result.okResult(articleDetailVo);
    }
}
