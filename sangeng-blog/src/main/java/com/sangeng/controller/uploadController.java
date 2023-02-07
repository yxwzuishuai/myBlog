package com.sangeng.controller;

import com.sangeng.result.Result;
import com.sangeng.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class uploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result upload(MultipartFile img){
       return uploadService.upload(img);

    }
}
