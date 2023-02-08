package com.sangeng.controller;


import com.sangeng.result.Result;
import com.sangeng.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
@Api("获取标签")
public class TagController {


    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Result list(){
        return Result.okResult(tagService.list());
    }
}
