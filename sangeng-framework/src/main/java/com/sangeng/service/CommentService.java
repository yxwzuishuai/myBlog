package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Comment;
import com.sangeng.result.Result;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-02-02 09:23:24
 */
public interface CommentService extends IService<Comment> {

    //查看友链评论和文章评论共用一个接口id
    Result commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    Result comment(Comment comment);

}

