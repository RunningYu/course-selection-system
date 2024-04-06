package courseselectionsystem.utils;


import cn.hutool.core.date.DateUtil;
import courseselectionsystem.config.MinIO.MinioProperties;
import courseselectionsystem.entity.UploadResponse;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2024/4/6
 * 文件操作工具类
 */

@Component
@Slf4j
public class MinioUtil {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioClient minioClient;

    /**
     * 创建bucket
     */
    public void createBucket(String bucketName) throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     */
    public UploadResponse uploadFile(MultipartFile file, String bucketName) {
        log.info("MinioUtil uploadFile bucketName:[{}]", bucketName);
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            return null;
        }
        try {
            //判断存储桶是否存在  不存在则创建
            createBucket(bucketName);
            //文件名
            String originalFilename = file.getOriginalFilename();
            //新的文件名 = 时间戳_随机数.后缀名
            assert originalFilename != null;
            long now = System.currentTimeMillis() / 1000;
            String fileName = DateUtil.format(DateUtil.date(),"yyyyMMdd")+"_"+ now + "_" + new Random().nextInt(1000) +
                    originalFilename.substring(originalFilename.lastIndexOf("."));

            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                    file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            String url = minioProperties.getEndpoint() + "/" + bucketName + "/" + fileName;
            return new UploadResponse(url);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败！");
        }
    }


}
