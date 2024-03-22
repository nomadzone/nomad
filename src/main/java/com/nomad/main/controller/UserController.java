package com.nomad.main.controller;
import cn.hutool.core.lang.hash.Hash;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.entity.Posts;
import com.nomad.main.entity.User;
import com.nomad.main.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    PartnerSearchService partnerSearchService;

    @Autowired
    PostsService postsService;

    @Autowired
    FanService fanService;

    @Autowired
    LikesService likesService;

    @Operation(summary = "修改头像", description = "form-data; key=>file,value=>图片。")
    @PostMapping("/changeAvatar/{id}")
    public ResultVo changeAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {

        if(id == null) {
            return ResultVo.failed("'id'不能为空.");
        }
        // TODO XXX: login user id
        Long loginUserId = 1L;
        if(id != loginUserId) {
            return ResultVo.failed("无权限修改他人头像");
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
    public ResultVo<User> getUserById(@PathVariable Long id) {
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

        // TODO XXX: login user id
        Long loginUserId = 1L;
        if(user.getId() != loginUserId) {
            return ResultVo.failed("无权限修改他人用户信息");
        }

        boolean b = userService.updateById(user);
        return ResultVo.success(null);
    }

    @Operation(summary = "获取个人社交信息", description = "")
    @GetMapping("/social/{userId}")
    public ResultVo<User> getSocialById(@PathVariable Long userId) {

        // 关注
        int followCount = fanService.countFollow(userId);
        // 粉丝
        int fanCount = fanService.countFan(userId);
        // 粉丝
        int likeCount = likesService.countLike(userId);

        // TODO: v1.1.0版本不统计对评论的点赞

        Map<String, Integer> map = new HashMap<>();
        map.put("follow", followCount);
        map.put("fan", fanCount);
        map.put("like", likeCount);
        return ResultVo.success(map);

    }

    @Operation(summary = "搭子-发布活动列表", description = "根据用户查询活动列表")
    @GetMapping("/listByUser/{userId}")
    public ResultVo<List<PartnerSearch>> listByUser(@PathVariable Long userId) {
        List<PartnerSearch> reList = partnerSearchService.findByUserId2(userId);
        return ResultVo.success(reList);
    }

    @Operation(summary = "发布内容list", description = "")
    @GetMapping("/list/{userId}")
    public ResultVo<List<Posts>> list(@PathVariable Long userId) {

        List<Posts> postsList = postsService.findByUserId(userId);
        return ResultVo.success(postsList);

    }

    @Operation(summary = "点赞内容list", description = "")
    @GetMapping("/likes/{userId}")
    public ResultVo<List<Posts>> likes(@PathVariable Long userId) {

        List<Posts> postsList = postsService.findLikesByUserId(userId);
        return ResultVo.success(postsList);

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
