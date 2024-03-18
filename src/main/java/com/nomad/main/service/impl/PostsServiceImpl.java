package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.Posts;
import com.nomad.main.mapper.PostsMapper;
import com.nomad.main.service.PostsService;
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
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements PostsService {

    @Autowired
    PostsMapper postsMapper;

    public List<Posts> findLikesByUserId(Long loginUserId) {
        List<Posts> list = postsMapper.findLikesByUserId(loginUserId);
        return list;
    }

    public List<Posts> findByUserId(Long loginUserId) {
        List<Posts> list = postsMapper.findByUserId(loginUserId);
        return list;
    }

}
