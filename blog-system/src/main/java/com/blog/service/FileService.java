package com.blog.service;

import com.blog.pojo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author a1387
 * @date 2023/02/23
 */
public interface FileService {
    /**
     * 上传图片
     *
     * @param image 图像
     * @return {@link ResponseResult}
     */
    ResponseResult uploadImage(MultipartFile image);
}
