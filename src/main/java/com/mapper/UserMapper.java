package com.mapper;


import com.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    int insert(UserEntity entity);

    void delete(@Param("id") Integer id);

    UserEntity getUserById(@Param("id") Integer id);



    void update(UserEntity entity);

    List<UserEntity> list();

    int userCount(@Param("area") String area,
                  @Param("tourist") String tourist,
                  @Param("company") String company,
                  @Param("guide") String guide);


}
