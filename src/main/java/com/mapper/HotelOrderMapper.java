package com.mapper;

import com.entity.HotelOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelOrderMapper {
    int hotelOrderInsert(HotelOrderEntity entity);

    int hotelOrderDelete(@Param("id") Integer id);

    HotelOrderEntity getHotelOrderById(@Param("id") Integer id);

    int hotelOrderUpdate(HotelOrderEntity entity);

    List<HotelOrderEntity> hotelOrderList();
}
