package com.service.impl;


import com.entity.UserEntity;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.UserService;

import java.util.List;

/**
 * @author zhangyao
 * createTime 2019-7-29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insert(UserEntity req) {
        try {
            userMapper.insert(req);
        }
        catch (Exception e){

        }

    }

    @Override
    public void update(UserEntity req) {

        userMapper.update(req);

    }


    @Override
    public void delete(UserEntity req) {
        userMapper.delete(req.getId());
    }

    @Override
    public UserEntity getById(Integer id) {
        UserEntity user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public List<UserEntity> list(UserEntity req) {

       List<UserEntity> list = userMapper.list();

        return list;
    }
}
