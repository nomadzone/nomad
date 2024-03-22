package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Fan;
import com.nomad.main.service.FanService;
import com.nomad.main.utils.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1/fan")
public class FanController {

    @Autowired
    FanService fanService;

    @Operation(summary = "关注接口", description = "参数'id'为被关注人的id")
    @PostMapping("/{userId}")
    public ResultVo follow(@PathVariable Long userId) {

        Long loginUserId = AuthUtil.getLoginUserId();

        // Prevent duplicate follow.
        Fan fan = fanService.findByUserId(userId, loginUserId);
        if(fan != null) {
            return ResultVo.failed("您已关注");
        }

        boolean save = fanService.save(
                Fan.builder()
                        .userId(userId)
                        .fanId(loginUserId)
                        .build()
        );

        return ResultVo.success(null);
    }

    @Operation(summary = "取消关注接口", description = "参数'id'为被关注人的id")
    @DeleteMapping("/{userId}")
    public ResultVo unfollow(@PathVariable Long userId) {

        Long loginUserId = AuthUtil.getLoginUserId();

        Fan fan = fanService.findByUserId(userId, loginUserId);
        if(fan == null) {
            return ResultVo.failed("您已取消关注");
        }

        boolean b = fanService.removeById(fan.getId());
        return ResultVo.success(null);

    }

}

