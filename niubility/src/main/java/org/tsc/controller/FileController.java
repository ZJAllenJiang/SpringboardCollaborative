package org.tsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tsc.service.FileProcessService;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileProcessService fileProcessService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartFile multipartFile) {
        fileProcessService.upload(multipartFile);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/download")
     public ResponseEntity<Resource> download(@RequestParam String key) {
        Resource download = fileProcessService.download(key);
        return ResponseEntity.ok().body(download);
    }
}
