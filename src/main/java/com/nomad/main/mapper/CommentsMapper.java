package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.entity.Comments;
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
public interface CommentsMapper extends BaseMapper<Comments> {

    List<Comments> getListByPostId(Long postId);

}
