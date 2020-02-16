package com.service;

import com.entity.UserEntity;

import java.util.List;

/**
 * @author zhangyao
 * createTime 2019-7-29
 */
public interface UserService {
    void insert(UserEntity req);

    void update(UserEntity req);

    void delete(UserEntity req);

    UserEntity getById(Integer id);

    List<UserEntity> list(UserEntity req);

}
