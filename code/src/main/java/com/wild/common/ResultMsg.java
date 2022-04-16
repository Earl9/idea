package com.wild.common;

import com.wild.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "统一请求的返回对象")
public class ResultMsg implements Serializable {

    @ApiModelProperty(value = "错误代码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "对应返回数据")
    private T data;

}
