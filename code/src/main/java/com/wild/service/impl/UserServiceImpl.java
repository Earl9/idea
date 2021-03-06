package com.wild.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.github.pagehelper.PageHelper;
import com.wild.common.GenerateUUID;
import com.wild.common.MyException;
import com.wild.common.PageResult;
import com.wild.dao.CommentRepository;
import com.wild.entity.Card;
import com.wild.entity.Comment;
import com.wild.entity.vo.UserExcelVo;
import com.wild.mapper.master.CardMapper;
import com.wild.mapper.master.UserMapper;
import com.wild.entity.User;
import com.wild.service.UserService;
import com.wild.utils.FileUtil;
import com.wild.utils.UploadDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CardMapper cardMapper;

    @Value("${file.path}")
    private String path;

    @Override

    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User queryById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Integer batchSave(List<User>  userList) {
        List<User> users = new ArrayList<>();
        userList.stream().forEach(x->{
            x.setUserId(new GenerateUUID().getUuid());
            users.add(x);
        });
        return userMapper.batchInsert(users);
    }

    @Override
    public Integer save(User user) {
        user.setUserId(new GenerateUUID().getUuid());
        return userMapper.insert(user);
    }

    @Override
    public Integer modify(User user) {
        return userMapper.update(user);
    }

    @Override
    public Integer del(String userId) {
        return userMapper.removeById(userId);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "????????????";
        }
        String filename = file.getOriginalFilename();
        InputStream inputStream = null;
        String name = null;
        try {
            inputStream = file.getInputStream();
            name = FileUtil.upload(inputStream, path, filename);
        }catch (Exception e){
            return "????????????";
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String ipPort = FileUtil.getServerIPPort(request);
        String link = ipPort+"/"+path+name;
        return link;
    }

    @Override
    public List<Comment> insert() {
//        Comment comment = new Comment();
//        comment.setId((new ObjectId()).toString());
//        comment.setCommentInfo("?????????");
//        comment.setUserName("admin");
//        Comment insert = mongoTemplate.insert(comment);
        List<Comment> commentList = commentRepository.findAll();

        return commentList;
    }

    @Override
    public String download(HttpServletResponse response, String fileName) {
//        File file = new File("src/main/resources/static/upload" +'/'+ fileName);
        File file = new File(path + fileName);

        if(!file.exists()){
            return "?????????????????????";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "????????????";
        }
        return "????????????";
    }

    @Override
    public PageResult pageList(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.list();
        return new PageResult(pageNum,pageSize,1,list);
    }

    @Override
    public String exportExcel(HttpServletResponse response,String fileName) {
        // ???????????? ?????????????????????swagger ??????????????????????????????????????????????????????postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // ??????URLEncoder.encode???????????????????????? ?????????easyexcel????????????
            String name = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + name + ".xlsx");
            List<User> list = userMapper.listAll();
            ArrayList<UserExcelVo> userExcelVos = new ArrayList<>();
            list.stream().forEach(x->{
                UserExcelVo userExcelVo = new UserExcelVo();
                userExcelVo.setUserName(x.getUserName());
                userExcelVo.setPassword(x.getPassWord());
                userExcelVo.setPhone(x.getPhone());
                userExcelVo.setDel(x.getDel().equals(0) ? "??????" : "??????");
                userExcelVo.setCreateTime(x.getCreateTime());
//                List<Card> cards = cardMapper.selectById(x.getUserId());
//                userExcelVo.setCards(cards);
                userExcelVos.add(userExcelVo);
            });
            EasyExcel.write(response.getOutputStream(), UserExcelVo.class).sheet("??????").doWrite(userExcelVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String uploadExcel(MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "Excel??????";
        }
        String link = null;
        try {
            //??????Excel
            EasyExcel.read(file.getInputStream(), UserExcelVo.class, new UploadDataListener(userMapper)).sheet().doRead();
            //????????????
            link = this.uploadFile(file, request);
        } catch (Exception e) {
            return "????????????";
        }
        return link;

    }

    //????????????????????????
    @Override
    public List<User> distinctList() {
        List<User> list = userMapper.list();

        List<User> distinctUser = new ArrayList<>();
        //??????1
        distinctUser = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(
                        o -> o.getUserName() + ";" + o.getPassWord()))), ArrayList::new));
        //??????2
        ArrayList<String> strings = new ArrayList<>();
        List<User> finalDistinctUser = distinctUser;
        list.forEach(x->{
            String userName = x.getUserName();
            String password = x.getPassWord();
            String s = userName + password;
            if (!strings.contains(s)){
                strings.add(s);
                finalDistinctUser.add(x);
            }
        });
        return distinctUser;
    }


}
