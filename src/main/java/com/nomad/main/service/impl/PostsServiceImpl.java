package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.Posts;
import com.nomad.main.mapper.PostsMapper;
import com.nomad.main.service.PostsService;
import org.springframework.stereotype.Service;

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

}
