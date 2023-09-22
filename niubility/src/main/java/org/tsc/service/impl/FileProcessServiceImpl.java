package org.tsc.service.impl;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tsc.service.FileProcessService;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileProcessServiceImpl implements FileProcessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessServiceImpl.class);

    public static String BUCKET = "tsc-bucket-0920";

    @Autowired
    private S3Template s3Template;

    @Override
    public void upload(MultipartFile multipartFile) {
        try (InputStream is = multipartFile.getInputStream()) {
            s3Template.upload(BUCKET, multipartFile.getOriginalFilename(), is,
                    ObjectMetadata.builder().contentType(multipartFile.getContentType()).build());
        } catch (IOException e) {
            LOGGER.error("Fail to upload file {}", multipartFile.getOriginalFilename());
        }
    }

    @Override
    public Resource download(String key) {
        S3Resource download = s3Template.download(BUCKET, key);
        if (!download.exists()) {
            LOGGER.error("Fail to download file given key {}", key);
        }
        return download;
    }
}
