package com.wild.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wild.common.PageResult;
import com.wild.entity.Comment;
import com.wild.entity.User;
import com.wild.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @ApiOperation("根据id查询")
    @ApiParam(name = "userId", value = "用户id", required = true)
    @GetMapping("/queryById")
    public User queryById(@RequestParam("userId") String userId) {
        return userService.queryById(userId);
    }

    @ApiOperation("批量插入用户")
    @ApiParam(name = "用户对象", value = "传入json格式", required = true)
    @PostMapping("/saveList")
    public Integer saveList(@RequestBody List<User> userList) {
        return userService.batchSave(userList);
    }

    @ApiOperation("保存用户")
    @ApiParam(name = "用户对象", value = "传入json格式", required = true)
    @PostMapping("/save")
    public Integer save(@RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation("修改用户")
    @ApiParam(name = "用户对象", value = "传入json格式", required = true)
    @PostMapping("/update")
    public Integer modify(@RequestBody User user) {
        return userService.modify(user);
    }

    @ApiOperation("删除用户")
    @ApiParam(name = "用户id", value = "userId", required = true)
    @PostMapping("/delete")
    public Integer modify(@RequestParam("userId") String userId) {
        return userService.del(userId);
    }

    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "file", value = "要上传的文件", required = true, dataType = "MultipartFile" ,dataTypeClass = MultipartFile.class)
    @PostMapping(value = "/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String result = userService.uploadFile(file,request);
        return result;
    }

    @ApiOperation(value = "文件下载")
    @PostMapping("/download")
    public String fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName){
        return userService.download(response,fileName);
    }

    @ApiOperation("分页查询")
    @ApiParam(name = "用户id", value = "userId", required = true)
    @PostMapping("/page")
    public PageResult page(@RequestParam(value = "pageNum" ,defaultValue = "0") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        PageResult userPageInfo = userService.pageList(pageNum,pageSize);
        return userPageInfo;
    }

    @ApiOperation(value = "Excel导出")
    @GetMapping ("/exportExcel")
    public String exportExcel(HttpServletResponse response ,@RequestParam("fileName") String fileName){
        return userService.exportExcel(response,fileName);
    }

    @ApiOperation(value = "Excel上传")
    @ApiImplicitParam(name = "file", value = "要上传的Excel", required = true, dataType = "MultipartFile" ,dataTypeClass = MultipartFile.class)
    @PostMapping(value = "/uploadExcel")
    public String uploadExcel(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String result = userService.uploadExcel(file,request);
        return result;
    }

    @ApiOperation(value = "集合去重")
    @PostMapping(value = "/distinctList")
    public List<User> distinctList() {
        List<User> users = userService.distinctList();
        return users;
    }
}
