package com.nomad.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nomad.main.entity.User;
import com.nomad.main.mapper.UserMapper;
import com.nomad.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

//    public boolean changeAvatar(User user) {
//        userMapper.updateById(user);
//        return true;
//    }

}
