package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.UserService;
import util.Response;
import util.ResponseCode;

import java.util.HashMap;

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/insert")
    public Response insert(@RequestBody UserInsertReq req){
        if (!(StringUtils.hasText(req.getAccount()) && StringUtils.hasText(req.getPassword()))) {
            return Response.create(ResponseCode.ERROR, "账户名和密码均不能为空", null);
        }
        if (null != userService.getByAccount(req.getAccount())) {
            return Response.create(ResponseCode.ERROR, "账户名已存在", null);
        }
        userService.insert(req);
        return Response.ok();
    }

    @PostMapping("/update")
    public Response update(@RequestBody UserUpdateReq req){
        userService.update(req);
        return Response.ok();
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestBody ListIdReq req, HashMap<String, String> log){
        log.put("account",userService.getUserAccount(req));
        userService.delete(req);
        return Response.ok();
    }

    @PostMapping("/list")
    //查询用户信息包括用户组、角色信息
    public Response list(@RequestBody UserQueryReq req){
        PageResult<UserEntity> pageResult = userService.list(req);
        return Response.ok(pageResult);
    }

}
