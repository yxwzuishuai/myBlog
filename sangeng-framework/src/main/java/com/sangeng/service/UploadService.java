package com.sangeng.service;

import com.sangeng.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result upload(MultipartFile img);
}
