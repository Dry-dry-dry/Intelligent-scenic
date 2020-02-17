package com.service.impl;

import com.entity.HotelOrderEntity;
import com.mapper.HotelOrderMapper;
import com.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelOrderServiceImpl implements HotelOrderService {
    @Autowired
    HotelOrderMapper hotelOrderMapper;
    @Override
    public int hotelOrderInsert(HotelOrderEntity req) {
        return hotelOrderMapper.hotelOrderInsert(req);
    }

    @Override
    public int hotelOrderUpdate(HotelOrderEntity req) {
        return hotelOrderMapper.hotelOrderUpdate(req);
    }

    @Override
    public int hotelOrderDelete(HotelOrderEntity req) {
        return hotelOrderMapper.hotelOrderDelete(req.getId());
    }

    @Override
    public HotelOrderEntity getHotelOrderById(Integer id) {
        return hotelOrderMapper.getHotelOrderById(id);
    }

    @Override
    public List<HotelOrderEntity> hotelOrderList() {
        return hotelOrderMapper.hotelOrderList();
    }
}
