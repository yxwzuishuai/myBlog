package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.entity.Dto.TagListDto;
import com.sangeng.entity.Tag;
import com.sangeng.entity.vo.PageVo;
import com.sangeng.entity.vo.TagVo;
import com.sangeng.mapper.TagMapper;
import com.sangeng.result.Result;
import com.sangeng.service.TagService;
import com.sangeng.util.BeanCopyUtils;
import com.sangeng.util.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-08 10:17:35
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {



    @Override
    public Result<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark, tagListDto.getRemark());
        Page<Tag> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return Result.okResult(pageVo);
    }

    @Override
    public Result insertTag(@RequestBody TagListDto tagListDto) {

        Tag tag = new Tag();
        tag.setName(tagListDto.getName());
        tag.setRemark(tagListDto.getRemark());
        save(tag);
        return Result.okResult();
    }

    @Override
    public Result updateTag(TagVo tagVo) {
        Tag tag = BeanCopyUtils.copyBean(tagVo, Tag.class);
        updateById(tag);
        return Result.okResult();
    }
}

