package com.mapper;

import com.entity.HotelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelMapper {
    int hotelInsert(HotelEntity entity);

    int hotelDelete(@Param("id") Integer id);

    HotelEntity getHotelById(@Param("id") Integer id);

    int hotelUpdate(HotelEntity entity);

    List<HotelEntity> hotelList();
}
