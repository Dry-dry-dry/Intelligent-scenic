package com.controller;

import com.entity.TicketEntity;
import com.service.TicketService;
import com.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "门票相关接口")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
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
    public Response getTicketByUser(@RequestParam String userName){
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
}
