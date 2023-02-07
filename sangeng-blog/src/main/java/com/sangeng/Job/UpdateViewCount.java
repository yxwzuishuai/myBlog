package com.sangeng.Job;


import com.sangeng.entity.Article;
import com.sangeng.service.ArticleService;
import com.sangeng.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Component
public class UpdateViewCount {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;
    //每五十分钟执行一次
    @Scheduled(cron = "0 0/50 * * * ?")
    public void UpdateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> articleViewCount = redisCache.getCacheMap("ArticleViewCount");
        //每一个entrySet包含一个 key和一个 value
        List<Article> collect = articleViewCount.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //更新到数据库中
        articleService.updateBatchById(collect);
    }
}
