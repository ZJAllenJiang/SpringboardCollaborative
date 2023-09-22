package org.tsc.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileProcessService {

    void upload(MultipartFile multipartFile);

    Resource download(String key);
}
