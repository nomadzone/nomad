package com.nomad.main.controller;


import com.nomad.main.dto.PostsVO;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Likes;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.entity.Posts;
import com.nomad.main.service.PostsService;
import com.nomad.main.utils.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Tag(name = "Posts", description = "Posts APIs")
@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Operation(summary = "发布帖子", description = "")
    @PostMapping()
    public ResultVo publish(@RequestBody Posts posts) {
        // check
        if(posts.getId() != null) {
            return ResultVo.failed("发布帖子时, 'id'不能被指定.");
        }
        if(posts.getTitle() == null) {
            return ResultVo.failed("'title'不能为空");
        }
        if(posts.getContent() == null) {
            return ResultVo.failed("'content'不能为空");
        }
        if(posts.getLatitude() == null) {
            return ResultVo.failed("'latitude'不能为空");
        }
        if(posts.getLongitude() == null) {
            return ResultVo.failed("'longitude'不能为空");
        }
        if(posts.getLocationName() == null) {
            return ResultVo.failed("'locationName'不能为空");
        }

        Long loginUserId = AuthUtil.getLoginUserId();
        posts.setUserId(loginUserId);
        posts.setCreatedAt(System.currentTimeMillis());
        posts.setUpdatedAt(System.currentTimeMillis());

        boolean save = postsService.save(posts);
        return ResultVo.success(null);

    }

    @Operation(summary = "修改帖子", description = "根据帖子id进行修改")
    @PutMapping()
    public ResultVo edit(@RequestBody Posts posts) {

        Posts oldPost = postsService.getById(posts.getId());
        Long loginUserId = AuthUtil.getLoginUserId();
        if(oldPost == null || oldPost.getUserId() != loginUserId) {
            return ResultVo.failed("不能修改他人帖子");
        }
        posts.setUpdatedAt(System.currentTimeMillis());
        boolean b = postsService.updateById(posts);
        return ResultVo.success(null);
    }

    @Operation(summary = "删除帖子", description = "根据id删除帖子")
    @DeleteMapping("/{id}")
    public ResultVo deletePostsById(@PathVariable Long id) {

        Long loginUserId = AuthUtil.getLoginUserId();
        Posts post = postsService.getById(id);
        if(post == null || post.getUserId() != loginUserId) {
            return ResultVo.failed("不能删除他人帖子");
        }

        boolean b = postsService.removeById(id);
        return ResultVo.success(null);
    }

    @Operation(summary = "查看帖子", description = "根据id查看帖子")
    @GetMapping("/{id}")
    public ResultVo<Posts> getById(@PathVariable Long id) {
        Posts post = postsService.getById(id);
        if(post == null) {
            return ResultVo.failed("帖子不存在");
        }
        return ResultVo.success(post);
    }

    @Operation(summary = "点赞内容list", description = "")
    @GetMapping("/likes")
    public ResultVo<List<Posts>> likes() {

        // TODO XXX: login user id
        Long loginUserId = 1L;

        List<Posts> postsList = postsService.findLikesByUserId(loginUserId);
        return ResultVo.success(postsList);

    }

    @Operation(summary = "发布内容list", description = "")
    @GetMapping("/list")
    public ResultVo<List<Posts>> list() {

        // TODO XXX: login user id
        Long loginUserId = 1L;

        List<Posts> postsList = postsService.findByUserId(loginUserId);
        return ResultVo.success(postsList);

    }

    @Operation(summary = "top3大家热议帖子-通过位置获取", description = "")
    @GetMapping("/getTop3/{locationName}")
    public ResultVo<List<Posts>> getTop3(@PathVariable("locationName")String locationName){
        return postsService.getTop3(locationName);
    }

    @Operation(summary = "获取帖子（怎么玩）-通过位置获取", description = "")
    @GetMapping("/getPosts/{locationName}")
    public ResultVo<List<PostsVO>> getPosts(@PathVariable("locationName")String locationName){
        return  postsService.getPosts(locationName);
    }

}
