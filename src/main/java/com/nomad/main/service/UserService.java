package com.nomad.main.service;

import com.nomad.main.dto.UserDto;
import com.nomad.main.entity.UserEntity;

/**
 * User service to handle all user related queries.
 */
public interface UserService {
    UserEntity createUser(UserDto request);
    UserEntity updateUser(UserDto request);
    UserEntity getUserById(Long id);
    void deleteUserById(Long id);

}
