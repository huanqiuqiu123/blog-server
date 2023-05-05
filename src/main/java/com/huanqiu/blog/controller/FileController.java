package com.huanqiu.blog.controller;

import com.huanqiu.blog.common.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/05/04 下午 6:59
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${web.upload-file-path}")
    private String uploadFilePath;

    @Value("${web.upload-img-path}")
    private String uploadImgPath;

    @PostMapping("/upload_img")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileSuffix = "png";
            if (StringUtils.hasText(file.getOriginalFilename())) {
                fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            String fileName = new Date().getTime()+ fileSuffix;
            file.transferTo(new File(uploadImgPath, fileName));
            fileNames.add(fileName);
        }
        return new ResponseResult(200, "文件上传成功", Map.of("fileNames", fileNames));
    }


}
