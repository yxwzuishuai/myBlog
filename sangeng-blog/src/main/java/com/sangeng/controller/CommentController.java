package com.sangeng.controller;


import com.sangeng.constants.Constants;
import com.sangeng.entity.Comment;
import com.sangeng.result.Result;
import com.sangeng.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //发表评论接口
    @PostMapping
    public Result comment(@RequestBody Comment comment){
        return commentService.comment(comment);
    }
    //展示评论
    @GetMapping("/commentList")
    public Result commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(Constants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    //友联评论列表
    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表")
    public Result linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.commentList(Constants.LINK_COMMENT,null,pageNum,pageSize);
    }


}
