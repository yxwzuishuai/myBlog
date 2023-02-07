package com.sangeng.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bing_  @create 2022/2/7-19:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 访问量
     */
    private Long viewCount;
}