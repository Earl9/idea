package com.wild.mapper.master;

import com.wild.entity.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CardMapper {

    List<Card> selectById(@Param("userId") String userId);
}
