package com.mapper;


import com.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    void insert(UserEntity entity);

    void delete(@Param("id") Integer id);

    UserEntity getUserById(@Param("id") Integer id);



    void update(UserEntity entity);

    List<UserEntity> list();


}
