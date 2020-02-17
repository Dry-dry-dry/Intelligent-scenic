package com.service;

import com.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyao
 * createTime 2019-7-29
 */
public interface UserService {
    int insert(UserEntity req);

    void update(UserEntity req);

    void delete(UserEntity req);

    UserEntity getById(Integer id);

    List<UserEntity> list(UserEntity req);

    int userCount(@Param("area") String area,
                  @Param("tourist") String tourist,
                  @Param("company") String company,
                  @Param("guide") String guide);

}
