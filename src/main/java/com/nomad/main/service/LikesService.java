package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.entity.Likes;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
public interface LikesService extends IService<Likes> {

    Likes findByPostId(Long postId, Long loginUserId);

    List<Likes> getByUserId(Long loginUserId);

    int countLike(Long userId);

}
