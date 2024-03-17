package com.nomad.main.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.User;
import com.nomad.main.mapper.UserMapper;
import com.nomad.main.service.ImageService;
import com.nomad.main.service.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.path}")
    private String imagePath;

    public ResultVo<String> uploadImage(MultipartFile file) throws IOException {

        // check
        if(file.isEmpty()) {
            return ResultVo.failed("文件为空");
        }

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

    public void downloadImage(String fileName, HttpServletResponse response) throws IOException {
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
