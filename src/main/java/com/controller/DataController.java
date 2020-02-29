package com.controller;

import com.entity.TicketEntity;
import com.service.TicketService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "人员流量统计接口")
@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private TicketService ticketService;

    @ApiOperation("出口流量统计")
    @PostMapping("/findUserByOut")
    public Response findUserByOutTime(@RequestParam String strInTime,@RequestParam String strOutTime){
        int outNum = ticketService.getTicketByOutTime(strInTime,strOutTime);
        return Response.ok(outNum);
    }

    @ApiOperation("入口流量统计")
    @PostMapping("/findUserByIn")
    public Response findUserByInTime(@RequestParam String strInTime,@RequestParam String strOutTime){
        int inNum = ticketService.getTicketByInTime(strInTime,strOutTime);
        return Response.ok(inNum);
    }

    @ApiOperation("不同时间景区流量统计")
    @PostMapping("/findUserByTime")
    public Response findUserByTime(@RequestParam String strStartTime,@RequestParam String strEndTime){
        List numList = ticketService.findUserByTime(strStartTime,strEndTime);
        return Response.ok(numList);
    }

    @ApiOperation("截止目前，景区经营数据统计")
    @PostMapping("/businessData")
    /**
     * 数据统计包括：景区售票数，总营业额，购票已进入景区人数，对用的营业额，购票未进入景区人数，对应营业额
     * */
    public Response businessData(){
        List list = ticketService.businessData();
        return Response.ok(list);
    }

}
