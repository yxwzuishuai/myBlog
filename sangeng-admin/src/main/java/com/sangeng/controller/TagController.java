package com.sangeng.controller;


import com.sangeng.entity.Dto.TagListDto;
import com.sangeng.entity.Tag;
import com.sangeng.entity.vo.PageVo;
import com.sangeng.entity.vo.TagVo;
import com.sangeng.result.Result;
import com.sangeng.service.TagService;
import com.sangeng.util.BeanCopyUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
@Api("获取标签")
public class TagController {


    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Result<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public Result insertTag(@RequestBody TagListDto tagListDto){
        return tagService.insertTag(tagListDto);

    }

    @DeleteMapping("/{id}")
    public Result deleteTag(@PathVariable("id") Long id){
        tagService.removeById(id);
        return Result.okResult();
    }

    @GetMapping("/{id}")
    public Result getTag(@PathVariable("id") Long id){
        Tag tag = tagService.getById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return Result.okResult(tagVo);
    }

    @PutMapping()
    public Result updateTag(@RequestBody TagVo tagVo){
        return Result.okResult(tagService.updateTag(tagVo));
    }

}
