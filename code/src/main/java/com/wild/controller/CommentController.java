package com.wild.controller;

import com.wild.common.ErrorResult;
import com.wild.common.MyException;
import com.wild.entity.Comment;
import com.wild.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "评论")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private UserService userService;

    @ApiOperation("新增")
    @GetMapping("/insert")
    public List<Comment> insert() {
        return userService.insert();
    }


    @ApiOperation("测试")
    @GetMapping("/test")
    public Date test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dates = null;
        try {
            String s = sdf.format(new Date());
            dates = Timestamp.valueOf(s);
//            date = sdf.parse(s);
            System.out.println(dates);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        throw new MyException(ErrorResult.nullError());
//        return dates;
    }
}
