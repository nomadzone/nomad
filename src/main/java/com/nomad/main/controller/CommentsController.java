package com.nomad.main.controller;


import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Comments;
import com.nomad.main.entity.Posts;
import com.nomad.main.service.CommentsService;
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
@Tag(name = "Comments", description = "Comments APIs")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;
    @Autowired
    PostsService postsService;

    @Operation(summary = "获取所有评论", description = "根据帖子id查看所有评论")
    @GetMapping("/{postId}")
    public ResultVo<List<Comments>> list(@PathVariable Long postId) {
        List<Comments> list = commentsService.getListByPostId(postId);
        return ResultVo.success(list);
    }

    @Operation(summary = "添加评论", description = "")
    @PostMapping()
    public ResultVo addComment(@RequestBody Comments comments) {

        if(comments.getPostId() == null) {
            return ResultVo.failed("'postId'不能为空");
        }
        if(comments.getContent() == null) {
            return ResultVo.failed("'content'不能为空");
        }

        // TODO status, 需要审核后才公开可见吗? 目前代码为默认公开可见
        // comments.setStatus(true); // 该字段无效

        comments.setId(null);
        Long loginUserId = AuthUtil.getLoginUserId();
        comments.setUserId(loginUserId);
        comments.setCreatedAt(System.currentTimeMillis());

        boolean save = commentsService.save(comments);
        return ResultVo.success(null);
    }

    @Operation(summary = "删除评论", description = "评论发布者 和 帖子发布者，都可以删除评论")
    @DeleteMapping("/{id}")
    public ResultVo deleteComments(@PathVariable Long id) {

        Comments comment = commentsService.getById(id);

        boolean authFlag = false;

        Long loginUserId = AuthUtil.getLoginUserId();
        if(comment != null && comment.getUserId() == loginUserId) {
            authFlag = true;
        }
        if(authFlag == false) {
            Long postId = comment.getPostId();
            Posts post = postsService.getById(postId);
            if(post != null && post.getUserId() == loginUserId) {
                authFlag = true;
            }
        }

        if(!authFlag) {
            return ResultVo.failed("您无权操作该条评论");
        }

        boolean b = commentsService.removeById(id);
        return ResultVo.success(null);

    }

}
