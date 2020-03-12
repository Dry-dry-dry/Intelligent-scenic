package com.controller;

import com.entity.TicketEntity;
import com.entity.TicketSetEntity;
import com.service.TicketService;
import com.service.TicketSetService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@Api(description = "门票相关接口")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketSetService ticketSetService;



    @ApiOperation("购买门票")
    @PostMapping("/buyTicket")
    public Response buyTicket(@RequestBody TicketEntity req){
        ticketService.insert(req);
        return Response.ok();
    }

    @ApiOperation("退票")
    @PostMapping("/refundTicket")
    public Response refundTicket(@RequestBody TicketEntity req){
        ticketService.update(req);
        return Response.ok();
    }

    @ApiOperation("补票")
    @PostMapping("/afterbuy")
    public Response afterbuy(@RequestBody TicketEntity req){
        ticketService.insert(req);
        return Response.ok();
    }

    @ApiOperation("查看自己的门票")
    @PostMapping("/getTicketByUser")
    //查询用户信息包括用户组、角色信息
    public Response getTicketByUser(@RequestBody TicketEntity req){
        String userName = req.getTicketbooker();
        List<TicketEntity> list = ticketService.getTicketByUser(userName);
        return Response.ok(list);
    }

    @ApiOperation("查看所有的门票信息")
    @PostMapping("/getAllTickets")
    //查询用户信息包括用户组、角色信息
    public Response getAllTickets(){
        List<TicketEntity> list = ticketService.list();
        return Response.ok(list);
    }

    @ApiOperation("景区入口：门票使用时间")
    @PostMapping("/ticketInTime")
    public Response setTicketInTime(@RequestBody TicketEntity req){
        String strInTime = req.getStrintime();
        long lt = new Long(strInTime);
        Date outDate = new Date(lt);
        TicketEntity ticketEntity = new TicketEntity();
//        ticketEntity.setId(req.getId());
        ticketEntity.setOuttime(outDate);
        ticketService.update(ticketEntity);
        return Response.ok();
    }
    @ApiOperation("景区出口：门票使用时间")
    @PostMapping("/ticketOutTime")
    public Response setTicketOutTime(@RequestBody TicketEntity req){
        String strOutTime = req.getStrouttime();
        long lt = new Long(strOutTime);
        Date outDate = new Date(lt);
        TicketEntity ticketEntity = new TicketEntity();
//        ticketEntity.setId(Integer.parseInt(ID));
        ticketEntity.setOuttime(outDate);
        ticketService.update(ticketEntity);
        return Response.ok();
    }

    /**
     * 门票设置接口
     *
     * */

    @ApiOperation("查询所有的门票设置信息")
    @PostMapping("/getAllTicketSet")
    //查询用户信息包括用户组、角色信息
    public Response getAllTicketSetEntitys(){
        List<TicketSetEntity> list = ticketSetService.list();
        return Response.ok(list);
    }

    @ApiOperation("增加门票设置信息")
    @PostMapping("/addTicketSet")
    //查询用户信息包括用户组、角色信息
    public Response addTicketSetEntitys(TicketSetEntity ticketSetEntity){
        ticketSetService.insert(ticketSetEntity);
        return Response.ok();
    }

    @ApiOperation("删除门票设置信息")
    @PostMapping("/deleteSetTickets")
    //查询用户信息包括用户组、角色信息
    public Response deleteTicketSetEntitys(@RequestBody TicketEntity req){
        String TicketID =String.valueOf( req.getId());
        ticketSetService.delete(TicketID);
        return Response.ok();
    }

    @ApiOperation("修改门票设置信息")
    @PostMapping("/updateSetTickets")
    //查询用户信息包括用户组、角色信息
    public Response updateTicketSetEntitys(TicketSetEntity ticketSetEntity){
        ticketSetService.update(ticketSetEntity);
        return Response.ok();
    }
}
