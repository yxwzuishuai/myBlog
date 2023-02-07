package com.sangeng.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;
    //在应用启动时把博客的浏览量存储到redis中
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  将 id和对应的 viewCount 存储到redis中
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();
                }));
        redisCache.setCacheMap("ArticleViewCount", viewCountMap);
    }
}
