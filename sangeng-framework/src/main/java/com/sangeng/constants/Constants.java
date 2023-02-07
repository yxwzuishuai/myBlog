package com.sangeng.constants;

/**
 * 全局常量
 * @author bing_  @create 2022/2/7-20:15
 */
public class Constants {

    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 状态：正常
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 友链状态为审核通过
     * */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 用户登陆时存入redis的key的前缀
     * */
    public static final String LOGIN_PREFIX = "blogLogin";

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
}
