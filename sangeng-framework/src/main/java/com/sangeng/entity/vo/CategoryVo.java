package com.sangeng.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author bing_  @create 2022/2/7-22:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sg_category")
@ApiModel(value = "CategoryVo 对象", description = "分类表")
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名")
    private String name;
}
