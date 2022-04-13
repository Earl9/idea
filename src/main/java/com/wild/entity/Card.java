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
@ApiModel(value="card",description="信用卡")
public class Card implements Serializable {

    @ApiModelProperty(value="Id",name="carId", required = true)
    private String cardId;

    @ApiModelProperty(value="userId",name="userId", required = true)
    private String userId;

    @ApiModelProperty(value="卡类型",name="cardName", required = true)
    private String cardName;

    @ApiModelProperty(value="卡号",name="cardNum", required = true)
    private Long cardNum;

    @ApiModelProperty(value="是否删除",name="del", required = false)
    private Integer del;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间",name="createTime", required = false)
    private Date createTime;
}
