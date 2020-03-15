package com.controller;

import com.entity.DataEntity;
import com.entity.ManagementEntity;
import com.entity.TicketEntity;
import com.service.TicketService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@Api(description = "人员流量统计接口")
@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private TicketService ticketService;

    @ApiOperation("出口流量统计")
    @PostMapping("/findUserByOut")
    public Response findUserByOutTime(@RequestBody DataEntity req){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strInTime = req.getStrInTime();
        String strOutTime = req.getStrOutTime();

        //如果传入的时间参数为null 则默认为今日流量统计
        Date intime = new Date();
        if(strInTime == null ){
            Long longInTime = null;
            try {
                longInTime = simpleDateFormat.parse(simpleDateFormat.format(intime)).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            strInTime = longInTime.toString();
        }
        //获取明天的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,+1);
        if(strOutTime == null){
            Long longOutTime = null;
            try {
                longOutTime = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime())).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            strOutTime = longOutTime.toString();
        }
        int outNum = ticketService.getTicketByOutTime(strInTime,strOutTime);
        return Response.ok(outNum);
    }

    @ApiOperation("入口流量统计")
    @PostMapping("/findUserByIn")
    public Response findUserByInTime(@RequestBody DataEntity req){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strInTime = req.getStrInTime();
        String strOutTime = req.getStrOutTime();
        //如果传入的时间参数为null 则默认为今日流量统计
        Date intime = new Date();
        if(strInTime == null ){
            String strInTiome = simpleDateFormat.format(intime);
            Long longInTime = null;
            try {
                longInTime = simpleDateFormat.parse(strInTiome).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            strInTime = longInTime.toString();
        }
        //获取明天的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,+1);
        if(strOutTime == null){
            Long longOutTime = null;
            try {
                longOutTime = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime())).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            strOutTime = longOutTime.toString();
        }
        int inNum = ticketService.getTicketByInTime(strInTime,strOutTime);
        return Response.ok(inNum);
    }

    @ApiOperation("不同时间景区流量统计")
    @PostMapping("/findUserByTime")
    public Response findUserByTime(@RequestBody DataEntity req){
        String strStartTime = req.getStrStartTime();
        String strEndTime = req.getStrEndTime();
        List numList = ticketService.findUserByTime(strStartTime,strEndTime);
        return Response.ok(numList.size());
    }



    @ApiOperation("景区每天经营数据统计")
    @PostMapping("/dayData")
    public Response dayData(@RequestBody DataEntity req){
        String strStartTime = req.getStrStartTime();
        String strEndTime = req.getStrEndTime();
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
    public Response monthData(@RequestBody DataEntity req){
        String strYear = req.getStrYear();
        String strMonth = req.getStrMonth();
        ManagementEntity managementEntity = ticketService.monthData(strYear , strMonth);
        return Response.ok(managementEntity);
    }

    @ApiOperation("景区年度经营数据统计")
    @PostMapping("/yearData")
    public Response yearData(@RequestBody DataEntity req){
        String strYear = req.getStrYear();
        ManagementEntity managementEntity = ticketService.yearData(strYear);
        return Response.ok(managementEntity);
    }
}
