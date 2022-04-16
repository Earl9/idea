package com.wild.controller;

import cn.hutool.core.util.ObjectUtil;
import com.wild.common.ResultMsg;
import com.wild.entity.User;
import com.wild.service.LoginService;
import com.wild.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "登录")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultMsg login(@RequestBody User user) {
        String msg = loginService.login(user);
        if ("success".equals(msg)){
            return new ResultMsg(200,msg,null);
        }else {
            return new ResultMsg(400,msg,null);
        }
    }

}
