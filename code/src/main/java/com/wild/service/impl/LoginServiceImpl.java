package com.wild.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.wild.common.GenerateUUID;
import com.wild.common.ResultMsg;
import com.wild.entity.User;
import com.wild.mapper.master.UserMapper;
import com.wild.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String login(User user) {
        if (user.getUserName() == null || user.getPassWord() == null || user.getPassWord() == "" || user.getUserName() == ""){
            return "用户名或密码为空";
        }
        String userName = user.getUserName();
        String passWord = userMapper.queryPassWordByUserName(userName);
        if (passWord != null && passWord.equals(user.getPassWord())){
/*            //生成Token
            Map<String, Object> map = new HashMap<String, Object>() {
                private static final long serialVersionUID = 1L;
                {
                    put("uid", passWord);
                    put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
                }
            };
            String token = JWTUtil.createToken(map, userName.getBytes());*/
            return "success";
        }
        return "error";
    }

    @Override
    public String register(User user) {
        if (user.getUserName() == null || user.getPassWord() == null || user.getPassWord() == "" || user.getUserName() == ""){
            return "用户名或密码为空";
        }
        user.setUserId(new GenerateUUID().getUuid());
        Integer save = userMapper.insert(user);
        if (save == 1){
            return "success";
        }
        return "error";
    }
}
