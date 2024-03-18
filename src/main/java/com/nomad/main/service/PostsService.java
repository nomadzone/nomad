package com.nomad.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nomad.main.entity.Posts;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
public interface PostsService extends IService<Posts> {

    List<Posts> findLikesByUserId(Long loginUserId);

    List<Posts> findByUserId(Long loginUserId);

}
