package com.wild.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.wild.common.ResultMsg;
import com.wild.entity.User;
import com.wild.mapper.master.UserMapper;
import com.wild.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String login(User user) {
        if (user.getUserName() == null || user.getPassWord() == null){
            return "error";
        }
        String userName = user.getUserName();
        String passWord = userMapper.queryPassWordByUserName(userName);
        if (passWord != null && passWord.equals(user.getPassWord())){
            return "success";
        }
        return "error";
    }
}
