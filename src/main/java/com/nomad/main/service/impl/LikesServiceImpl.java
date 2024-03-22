package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.Likes;
import com.nomad.main.mapper.LikesMapper;
import com.nomad.main.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {

    @Autowired
    LikesMapper likesMapper;

    public Likes findByPostId(Long postId, Long loginUserId) {
        Likes like = likesMapper.findByPostId(postId, loginUserId);
        return like;
    }

    public List<Likes> getByUserId(Long loginUserId) {
        List<Likes> list = likesMapper.getByUserId(loginUserId);
        return list;
    }

    public int countLike(Long userId) {
        return likesMapper.countLike(userId);
    }

}
