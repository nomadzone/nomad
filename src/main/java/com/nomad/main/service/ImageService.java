package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
public interface ImageService {

    public ResultVo<String> uploadImage(MultipartFile file) throws IOException;

    public void downloadImage(String fileName, HttpServletResponse response) throws IOException;

}
