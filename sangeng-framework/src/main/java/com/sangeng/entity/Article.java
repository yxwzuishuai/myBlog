package com.sangeng.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.poi.hssf.record.FtCblsSubRecord;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author Bing
 * @since 2022-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sg_article")
@ApiModel(value = "Article对象", description = "文章表")
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "所属分类id")
    @TableField("category_id")
    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    @ApiModelProperty(value = "是否置顶（0否，1是）")
    @TableField("is_top")
    private String top;

    @ApiModelProperty(value = "状态（0已发布，1草稿）")
    private String status;

    @ApiModelProperty(value = "访问量")
    @TableField("view_count")
    private Long viewCount;

    @ApiModelProperty(value = "是否允许评论 1是，0否")
    @TableField("is_comment")
    private String isComment;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;


}
