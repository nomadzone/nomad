package com.nomad.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nomad.main.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 4pmtong
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
