package com.nomad.main.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Image controller
 */
@Tag(name = "Image", description = "Image APIs")
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Operation(summary = "上传图片", description = "每次上传一张, 多张图片可异步调用。\r\nform-data; key=>file,value=>图片。")
    @PostMapping("/upload")
    public ResultVo<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    /**
     * TODO: Nginx can be used to optimize performance
     * @param fileName
     * @param response
     * @throws IOException
     */
    @Operation(summary = "下载图片", description = "将上传图片返回的图片名作为参数。")
    @GetMapping("/download/{imageName}")
    public void downloadImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        imageService.downloadImage(fileName, response);
    }

}
