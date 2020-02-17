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
    public int insert(UserEntity req) {


            int insert = userMapper.insert(req);
            return insert;

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

    @Override
    public int userCount(String area, String tourist, String company, String guide) {
        return userMapper.userCount(area,tourist,company,guide);
    }
}
