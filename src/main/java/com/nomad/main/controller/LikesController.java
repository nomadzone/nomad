package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Likes;
import com.nomad.main.service.LikesService;
import com.nomad.main.utils.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Tag(name = "Likes", description = "Likes APIs")
@RestController
@RequestMapping("/api/v1/likes")
public class LikesController {

    @Autowired
    LikesService likesService;

    @Operation(summary = "点赞", description = "参数: 帖子id")
    @PostMapping("/{postId}")
    public ResultVo like(@PathVariable Long postId) {

        Long loginUserId = AuthUtil.getLoginUserId();

        Likes like = Likes.builder()
                .postId(postId)
                .userId(loginUserId)
                .createdAt(System.currentTimeMillis())
                .build();

        // Prevent duplicate likes.
        Likes haveLike = likesService.findByPostId(postId, loginUserId);
        if(haveLike != null) {
            return ResultVo.failed("您已经点赞");
        }

        boolean save = likesService.save(like);
        return ResultVo.success(null);
    }

    @Operation(summary = "取消点赞", description = "参数: 帖子id")
    @DeleteMapping("/{postId}")
    public ResultVo unlike(@PathVariable Long postId) {

        Long loginUserId = AuthUtil.getLoginUserId();

        Likes like = likesService.findByPostId(postId, loginUserId);
        if(like == null) {
            return ResultVo.failed("您已取消点赞");
        }

        boolean b = likesService.removeById(like.getId());
        return ResultVo.success(null);
    }

}
