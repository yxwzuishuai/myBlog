package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Dto.TagListDto;
import com.sangeng.entity.Tag;
import com.sangeng.entity.vo.PageVo;
import com.sangeng.entity.vo.TagVo;
import com.sangeng.result.Result;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-02-08 10:17:35
 */
public interface TagService extends IService<Tag> {

    Result<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    Result insertTag(TagListDto tagListDto);

    Result updateTag(TagVo tagVo);
}

