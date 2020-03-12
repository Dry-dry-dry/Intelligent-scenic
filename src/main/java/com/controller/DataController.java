package com.controller;

import com.entity.ManagementEntity;
import com.entity.TicketEntity;
import com.service.TicketService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
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



    @ApiOperation("景区每天经营数据统计")
    @PostMapping("/dayData")
    public Response dayData(@RequestParam String strStartTime,@RequestParam String strEndTime){
        Date startTime = ticketService.timesTempToDate(strStartTime);
        Date endTime = ticketService.timesTempToDate(strEndTime);
        Map<String,Integer> map = ticketService.dayData(startTime, endTime);
        return Response.ok(map);
    }

    @ApiOperation("景区月度经营数据统计")
    @PostMapping("/monthData")
    /**
     * 数据统计包括：景区售票数，总营业额，购票已进入景区人数，对用的营业额，购票未进入景区人数，对应营业额
     * 参数输入如下
     * strYear: 2020
     * strMonth: 3
     * */
    public Response monthData(@RequestParam String strYear,@RequestParam String strMonth){
        ManagementEntity managementEntity = ticketService.monthData(strYear , strMonth);
        return Response.ok(managementEntity);
    }

    @ApiOperation("景区年度经营数据统计")
    @PostMapping("/yearData")
    public Response yearData(@RequestParam String strYear){
        ManagementEntity managementEntity = ticketService.yearData(strYear);
        return Response.ok(managementEntity);
    }
}
