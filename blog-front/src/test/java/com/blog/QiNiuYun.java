package com.blog;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * 七牛云测试
 *
 * @author a1387
 * @date 2023/02/23
 */
@SpringBootTest
public class QiNiuYun {
    @Value("${oss.accessKey}")
    private String ACCESS_KEY;
    @Value("${oss.secretKey}")
    private String SECRET_KEY;
    @Value("${oss.bucket}")
    private String BUCKET;
    @Test
    public void ossTest(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //密钥
        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;
        //创建的存储空间名称
        String bucket = BUCKET;
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //linux格式 /home/qiniu/test.png
        String localFilePath = "D:\\test.jpg";

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "test";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }
}
