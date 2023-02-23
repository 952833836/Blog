package com.blog.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import com.blog.common.SystemConstants;
import com.blog.enums.HttpResponseCode;
import com.blog.exception.SystemException;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.dto.LoginUser;
import com.blog.pojo.entity.User;
import com.blog.service.FileService;
import com.blog.service.UserService;
import com.blog.pojo.util.PathUtil;
import com.blog.pojo.util.RedisUtil;
import com.blog.pojo.util.SecurityUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 文件服务impl
 *
 * @author a1387
 * @date 2023/02/23
 */
@Service
@ConfigurationProperties("oss")
@Data
public class FileServiceImpl implements FileService {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String domainName;

    @Override
    public ResponseResult uploadImage(MultipartFile image) {
        //TODO 判断上传的图片类型是否为jpeg，png格式
        String contentType = image.getContentType();
        if(!contentType.endsWith("jpeg")&&!contentType.endsWith("png")){
            throw new SystemException(HttpResponseCode.INCORRECT_PICTURE_FORMAT);
        }
        //上传至七牛云
        String originalFilename = image.getOriginalFilename();
        String name = FileNameUtil.getName(originalFilename);
        name= PathUtil.generateFilePath(name);
        String path = uploadByQiNiuYun(image, name);
        return ResponseResult.okResult(path);
    }

    /**
     * 上传文件到七牛云
     *
     * @param file 文件
     * @return {@link String}
     */
    private String uploadByQiNiuYun(MultipartFile file,String fileName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String bucket = this.bucket;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken,null,null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return domainName+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                throw new RuntimeException("上传失败");
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
