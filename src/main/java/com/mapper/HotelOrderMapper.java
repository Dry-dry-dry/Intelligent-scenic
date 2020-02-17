package com.mapper;

import com.entity.HotelOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface HotelOrderMapper {
    int hotelOrderInsert(HotelOrderEntity entity);

    int hotelOrderDelete(@Param("id") Integer id);

    HotelOrderEntity getHotelOrderById(@Param("id") Integer id);

    int hotelOrderUpdate(HotelOrderEntity entity);

    List<HotelOrderEntity> hotelOrderList();
}
