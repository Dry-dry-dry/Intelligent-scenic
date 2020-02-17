package com.controller;

import com.entity.HotelEntity;
import com.service.HotelService;
import com.util.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(description = "宾馆相关接口")
@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @PostMapping("/hotelInsert")
    public Response hotelInsert(@RequestBody HotelEntity req){ //增加宾馆
        int i = hotelService.hotelInsert(req);
        return Response.ok(i);
    }
    @PostMapping("/hotelUpdate")
    public Response hotelUpdate(@RequestBody HotelEntity req){ //更新宾馆信息
        int i = hotelService.hotelUpdate(req);
        return Response.ok(i);
    }
    @DeleteMapping("/hotelDelete")
    public Response hotelDelete(@RequestBody HotelEntity req){//删除宾馆信息
        int i = hotelService.hotelDelete(req);
        return Response.ok(i);
    }

    @PostMapping("/hotelList")
    public Response hotelList(){  //宾馆列表
        List<HotelEntity> hotelEntities = hotelService.hotelList();
        return Response.ok(hotelEntities);
    }
    @PostMapping("/getHotelById")
    public Response getById(@RequestBody HotelEntity req){//查找某一宾馆
        HotelEntity hotelById = hotelService.getHotelById(req.getId());
        return Response.ok(hotelById);
    }
}
