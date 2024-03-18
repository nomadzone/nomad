package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.Comments;
import com.nomad.main.mapper.CommentsMapper;
import com.nomad.main.service.CommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    @Autowired
    CommentsMapper commentsMapper;

    public List<Comments> getListByPostId(Long postId) {
        List<Comments> list = commentsMapper.getListByPostId(postId);
        return list;
    }

}
