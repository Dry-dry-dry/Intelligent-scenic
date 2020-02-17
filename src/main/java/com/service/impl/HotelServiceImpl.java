package com.service.impl;

import com.entity.HotelEntity;
import com.mapper.HotelMapper;
import com.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public int hotelInsert(HotelEntity req) {
        return hotelMapper.hotelInsert(req);
    }

    @Override
    public int hotelUpdate(HotelEntity req) {
        return hotelMapper.hotelUpdate(req);

    }

    @Override
    public int hotelDelete(HotelEntity req) {
        return hotelMapper.hotelDelete(req.getId());
    }

    @Override
    public HotelEntity getHotelById(Integer id) {
        return hotelMapper.getHotelById(id);
    }

    @Override
    public List<HotelEntity> hotelList() {
        return hotelMapper.hotelList();
    }
}
