package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.entity.Comments;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
public interface CommentsService extends IService<Comments> {

    List<Comments> getListByPostId(Long postId);

}
