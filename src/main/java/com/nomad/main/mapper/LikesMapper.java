package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.dto.LikesTopVo;
import com.nomad.main.entity.Likes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Mapper
public interface LikesMapper extends BaseMapper<Likes> {

    Likes findByPostId(Long postId, Long loginUserId);

    List<Likes> getByUserId(Long loginUserId);

    List<LikesTopVo> getTop3(List<Long> postIds);
    List<LikesTopVo> getTop(List<Long> postIds);
}
