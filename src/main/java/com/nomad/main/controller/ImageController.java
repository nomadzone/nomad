package com.nomad.main.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.nomad.main.dto.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
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

    @Value("${image.path}")
    private String imagePath;

    @Operation(summary = "上传图片", description = "每次上传一张, 多张图片可异步调用。\r\nform-data; key=>file,value=>图片。")
    @PostMapping("/upload")
    public ResultVo uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        String ROOT_PATH = imagePath + File.separator;
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String extName = FileUtil.extName(originalFilename);
        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH);
        }
        originalFilename = IdUtil.simpleUUID() + suffix;
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        // save local disk
        file.transferTo(saveFile);

        return ResultVo.success(originalFilename);
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

        String ROOT_PATH = imagePath + File.separator;
        // 1. Input stream, reading file content through input stream
        FileInputStream fileInputStream = new FileInputStream(new File(ROOT_PATH + File.separator + fileName));
        // 2. Response output stream, write the file back to the browser
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        // 3. Close stream
        outputStream.close();
        fileInputStream.close();

    }

}
