package com.service;

import com.entity.HotelOrderEntity;

import java.util.List;

public interface HotelOrderService {
    int hotelOrderInsert(HotelOrderEntity req);

    int hotelOrderUpdate(HotelOrderEntity req);

    int hotelOrderDelete(HotelOrderEntity req);

    HotelOrderEntity getHotelOrderById(Integer id);

    List<HotelOrderEntity> hotelOrderList( );
}
