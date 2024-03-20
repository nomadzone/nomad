package com.nomad.main.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.dto.LikesTopVo;
import com.nomad.main.dto.PostsVO;
import com.nomad.main.dto.ResultVo;
import com.nomad.main.entity.Likes;
import com.nomad.main.entity.PartnerSearch;
import com.nomad.main.entity.Posts;
import com.nomad.main.mapper.LikesMapper;
import com.nomad.main.mapper.PostsMapper;
import com.nomad.main.service.LikesService;
import com.nomad.main.service.PostsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    LikesMapper likesMapper;

    public List<Posts> findLikesByUserId(Long loginUserId) {
        List<Posts> list = postsMapper.findLikesByUserId(loginUserId);
        return list;
    }

    public List<Posts> findByUserId(Long loginUserId) {
        List<Posts> list = postsMapper.findByUserId(loginUserId);
        return list;
    }

    @Override
    public ResultVo<List<Posts>> getTop3(String locationName) {
        if(StringUtils.isEmpty(locationName)){
            return ResultVo.failed("位置信息不能为空");
        }
        List<Posts> top3 = new ArrayList<>();
        //根据位置获取对于的帖子
        LambdaQueryWrapper<Posts> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Posts::getLocationName,locationName);
        List<Posts> searches = postsMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(searches)){
            List<Long> postIds = searches.stream().map(Posts::getId).collect(Collectors.toList());
            //获取top3点赞
            List<LikesTopVo> likesTopVos = likesMapper.getTop3(postIds);
            if(CollectionUtil.isNotEmpty(likesTopVos)){
                Map<Long,LikesTopVo> postId2Likes =  likesTopVos.stream().collect(Collectors.toMap(LikesTopVo::getPostId,o->o,(ov,nv)->nv));
                searches.forEach(posts -> {
                    Long id = posts.getId();
                    if(Objects.nonNull(postId2Likes.get(id))){
                        top3.add(posts);
                    }
                });
                return ResultVo.success(top3);
            }
        }
        return null;
    }

    @Override
    public ResultVo<List<PostsVO>> getPosts(String locationName) {
        if(StringUtils.isEmpty(locationName)){
            return ResultVo.failed("位置信息不能为空");
        }
        //根据位置获取对于的帖子
        LambdaQueryWrapper<Posts> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Posts::getLocationName,locationName);
        List<Posts> searches = postsMapper.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(searches)){
            List<PostsVO> postsVOS = BeanUtil.copyToList(searches, PostsVO.class);
            List<Long> postIds = searches.stream().map(Posts::getId).collect(Collectors.toList());
            //获取分组 根据分组获取对应的点赞数
            List<LikesTopVo> likesTopVos = likesMapper.getTop(postIds);
            if(CollectionUtil.isNotEmpty(likesTopVos)){
                Map<Long,LikesTopVo> postId2Likes =  likesTopVos.stream().collect(Collectors.toMap(LikesTopVo::getPostId,o->o,(ov,nv)->nv));
                postsVOS.forEach(posts -> {
                    Long id = posts.getId();
                    if(Objects.nonNull(postId2Likes.get(id))){
                        posts.setLikes(postId2Likes.get(id).getCount());//获取点赞数
                    }
                });
                return ResultVo.success(postsVOS);
            }
        }
        return null;
    }
}
