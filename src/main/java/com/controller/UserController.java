package com.controller;

import com.entity.UserCountReq;
import com.entity.UserEntity;
import com.service.UserService;
import com.util.Response;
import com.util.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(description = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("增加用户信息，id可不填，自增")
    @PostMapping("/insert")
    public Response insert(@RequestBody UserEntity req){
        if (!(StringUtils.hasText(req.getUsername()) && StringUtils.hasText(req.getPwd()))) {
            return Response.create(ResponseCode.ERROR, "账户名和密码均不能为空", null);
        }
        int insert = userService.insert(req);
        return Response.ok(insert);
    }
    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public Response update(@RequestBody UserEntity req){
        userService.update(req);
        return Response.ok();
    }
    @ApiOperation("删除用户信息，只填id即可")
    @DeleteMapping("/delete")
    public Response delete(@RequestBody UserEntity req){
        userService.delete(req);
        return Response.ok();
    }
    @ApiOperation("用户信息列表")
    @PostMapping("/list")
    public Response list(@RequestBody UserEntity req){
        List<UserEntity> list = userService.list(req);
        return Response.ok(list);
    }
    @ApiOperation("根据id查询用户信息，只填id即可")
    @PostMapping("/getById")
    public Response getById(@RequestBody UserEntity req){
        UserEntity userInfo = userService.getById(req.getId());
        return Response.ok(userInfo);
    }
    @ApiOperation("根据不同条件查询团队人数")
    @PostMapping("countByReq")  //根据不同的条件去查询团队人数
    public Response countByReq(@RequestBody UserCountReq req){
        int i = userService.userCount(req.getArea(), req.getTourist(), req.getCompany(), req.getGuide());
        return Response.ok(i);
    }

}
