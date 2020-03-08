package com.controller;

import com.entity.HotelOrderEntity;
import com.service.HotelOrderService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "宾馆详细相关接口")
@RestController
@RequestMapping("/api/hotelorder")
public class HotelOrderController {
    @Autowired
    HotelOrderService hotelOrderService;
    @ApiOperation("增加宾馆房间信息，id可不填，自增")
    @PostMapping("/hotelOrderInsert")
    public Response hotelOrderInsert(@RequestBody HotelOrderEntity req){ //增加房间信息
        int i = hotelOrderService.hotelOrderInsert(req);
        return Response.ok(i);
    }
    @ApiOperation("修改宾馆房间信息")
    @PostMapping("/hotelOrderUpdate")
    public Response hotelOrderUpdate(@RequestBody HotelOrderEntity req){ //更新房间信息
        int i = hotelOrderService.hotelOrderUpdate(req);
        return Response.ok(i);
    }
    @ApiOperation("删除宾馆房间信息，只填id即可")
    @DeleteMapping("/hotelOrderDelete")
    public Response hotelOrderDelete(@RequestBody HotelOrderEntity req){//删除房间信息
        int i = hotelOrderService.hotelOrderDelete(req);
        return Response.ok(i);
    }
    @ApiOperation("宾馆房间信息列表")
    @PostMapping("/hotelList")
    public Response hotelList(){  //房间列表
        List<HotelOrderEntity> hotelEntities = hotelOrderService.hotelOrderList();
        return Response.ok(hotelEntities);
    }
    @ApiOperation("根据id查询宾馆详细信息")
    @PostMapping("/getHotelById")
    public Response getById(@RequestBody HotelOrderEntity req){//查找某一房间
        HotelOrderEntity hotelById = hotelOrderService.getHotelOrderById(req.getId());
        return Response.ok(hotelById);
    }
}
