package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiaPeng
 * @since 2024-03-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
