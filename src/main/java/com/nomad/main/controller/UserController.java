package com.nomad.main.controller;
import com.nomad.main.dto.UserDto;
import com.nomad.main.entity.UserEntity;
import com.nomad.main.service.UserService;
import com.nomad.main.validation.OnUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller - used to get user info
 */
@Tag(name = "User", description = "User APIs")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/{id}")
    public UserEntity getMonitorAlertById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public UserEntity createUser(@RequestBody @Valid UserDto request) {
        return userService.createUser(request);
    }

    @PutMapping()
    public UserEntity updateUser(@RequestBody @Validated(OnUpdate.class)
                                                 UserDto request) {
        return userService.updateUser(request);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
