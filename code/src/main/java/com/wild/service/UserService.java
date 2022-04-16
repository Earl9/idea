package com.wild.service;

import com.wild.common.PageResult;
import com.wild.entity.Comment;
import com.wild.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    List<User> list();

    User queryById(String userId);

    Integer batchSave(List<User>  userList);

    Integer save(User user);

    Integer modify(User user);

    Integer del(String userId);

    String uploadFile(MultipartFile file, HttpServletRequest request);

    List<Comment> insert();

    String download(HttpServletResponse response, String fileName);

    PageResult pageList(Integer pageNum, Integer pageSize);

    String exportExcel(HttpServletResponse response,String fileName);

    String uploadExcel(MultipartFile file, HttpServletRequest request);

    List<User> distinctList();
}
