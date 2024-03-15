package com.nomad.main.service;

import com.nomad.main.dto.UserDto;
import com.nomad.main.entity.UserEntity;
import com.nomad.main.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation of user service.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserEntity createUser(UserDto request) {
//        validateConditions(request);

        var user = UserEntity.builder()
                .openId(request.getOpenId())
                .unionId(request.getUnionId())
                .nickname(request.getNickname())
                .signature(request.getSignature())
                .gender(request.getGender())
                .phone(request.getPhone())
                .email(request.getEmail())
                .description(request.getDescription())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .build();

        userMapper.insert(user);

        return user;
    }

    @Override
    public UserEntity updateUser(UserDto request) {
        return null;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
