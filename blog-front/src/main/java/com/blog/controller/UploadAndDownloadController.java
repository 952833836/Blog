package com.blog.controller;

import com.blog.pojo.ResponseResult;
import com.blog.service.FileService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 上传和下载控制器
 *
 * @author a1387
 * @date 2023/02/23
 */
@RestController
@RequestMapping("/files")
@Validated
public class UploadAndDownloadController {

    @Resource
    FileService fileService;

    @PostMapping("/uploadImage")
    public ResponseResult uploadImag(@RequestParam("image") @NotNull MultipartFile image){
        return fileService.uploadImage(image);
    }
}
