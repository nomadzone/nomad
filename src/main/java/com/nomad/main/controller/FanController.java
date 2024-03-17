package com.nomad.main.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Tag(name = "Fan", description = "Fan APIs")
@RestController
@RequestMapping("/fan")
public class FanController {

}

