package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.Exception.SystemException;
import com.sangeng.constants.Constants;
import com.sangeng.entity.Comment;

import com.sangeng.entity.vo.CommentVo;
import com.sangeng.entity.vo.PageVo;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-02 09:23:25
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    //查看评论
    @Override
    public Result commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Constants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.eq(Comment::getType, commentType);
        //将分页查询到的数据传入pageComment中
        Page<Comment> pageComment = new Page<>(pageNum,pageSize);
        page(pageComment, queryWrapper);

        List<CommentVo> commentVo = toCommentVoList(pageComment.getRecords());
        for(CommentVo commentVos:commentVo){
            commentVos.setChildren(getChildren(commentVos.getId()));
        }

        return Result.okResult(new PageVo(commentVo, pageComment.getTotal()));
    }

    //查询评论下的子评论
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(list);
        return commentVos;
    }

    //将comment转为commentVo，并设置一些comment中没有的值
    private List<CommentVo> toCommentVoList(List<Comment> records) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(records, CommentVo.class);
/*
        for(CommentVo commentVo:commentVos){
            String userName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(userName);
            if(commentVo.getToCommentUserId() != -1){
                String nickName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(nickName);
            }
        }*/

        commentVos.stream()
                    .map(commentVo -> {
                                commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName());
                                if (commentVo.getToCommentUserId() != -1) {
                                    String nickName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                                    commentVo.setToCommentUserName(nickName);
                                }
                                return commentVo;
                            }
                    );


        return commentVos;
    }


    //发表评论
    @Override
    public Result comment(Comment comment) {
        /*判断是否携带token,获取用户id   在MyMetaObjectHandler中设置了自动填充
        Long userId = SecurityUtils.getUserId();
        comment.setCreateBy(userId);*/
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(ResultEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return Result.okResult();
    }


}


