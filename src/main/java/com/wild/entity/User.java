package com.wild.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@ApiModel(value="user对象",description="用户对象")
public class User implements Serializable {

    @ApiModelProperty(value="用户Id",name="userId", required = true)
    private String userId;

    @ApiModelProperty(value="用户名",name="userName", required = true)
    private String userName;

    @ApiModelProperty(value="密码",name="password", required = true)
    private String password;

    @ApiModelProperty(value="手机号",name="phone", required = true)
    private String phone;

    @ApiModelProperty(value="是否删除",name="del", required = false)
    private Integer del;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间",name="createTime", required = false)
    private Date createTime;

    @ApiModelProperty(value="卡片集合",name="cards", required = false)
    private List<Card> cards;
}
