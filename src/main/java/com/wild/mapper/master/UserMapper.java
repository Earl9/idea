package com.wild.mapper.master;

import com.wild.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> list();

    User selectById(@Param("userId") String userId);

    Integer batchInsert(@Param("userList") List<User> userList);

    Integer insert(@Param("user") User user);

    Integer update(@Param("user") User user);

    Integer removeById(@Param("userId") String userId);

    List<User> list(Integer pageNum, Integer pageSize);

    List<User> listAll();
}
