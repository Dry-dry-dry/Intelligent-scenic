package com.service;

import com.entity.HotelEntity;

import java.util.List;

public interface HotelService {
    int hotelInsert(HotelEntity req);

    int hotelUpdate(HotelEntity req);

    int hotelDelete(HotelEntity req);

    HotelEntity getHotelById(Integer id);

    List<HotelEntity> hotelList( );
}
