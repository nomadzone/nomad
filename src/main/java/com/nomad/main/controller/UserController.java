package com.nomad.main.controller;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.User;
import com.nomad.main.service.ImageService;
import com.nomad.main.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * User Controller - used to get user info
 */
@Tag(name = "User", description = "User APIs")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Resource
    UserService userService;

    @Autowired
    ImageService imageService;

    @Operation(summary = "修改头像", description = "form-data; key=>file,value=>图片。")
    @PostMapping("/changeAvatar/{id}")
    public ResultVo changeAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {

        if(id == null) {
            return ResultVo.failed("'id'不能为空.");
        }

        ResultVo<String> stringResultVo = imageService.uploadImage(file);
        String fileName = stringResultVo.getData();

        User user = new User();
        user.setId(id);
        user.setAvatar(fileName);

        boolean b = userService.updateById(user);
        if(b) {
            return ResultVo.success(null);
        } else {
            return ResultVo.failed(null);
        }
    }

    @Operation(summary = "获取个人信息", description = "")
    @GetMapping("/{id}")
    public ResultVo<User> getMonitorAlertById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResultVo.success(user);
    }

    @Operation(summary = "更新个人信息", description = "")
    @PutMapping()
//    public ResultVo updateUser(@RequestBody @Validated(OnUpdate.class) UserDto request) {
    public ResultVo updateUser(@RequestBody User user) {
        if(user.getId() == null) {
            return ResultVo.failed("'id'不能为空.");
        }
        boolean b = userService.updateById(user);
        return ResultVo.success(null);
    }


//    @PostMapping()
//    public User createUser(@RequestBody @Valid UserDto request) {
    public ResultVo createUser(@RequestBody @Valid User request) {
        boolean save = userService.save(request);
        return ResultVo.success(null);
    }

//    @DeleteMapping("/{id}")
    public ResultVo deleteUserById(@PathVariable Long id) {
        boolean b = userService.removeById(id);
        return ResultVo.success(null);
    }
}
