package com.wild.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wild.entity.Card;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExcelVo {

    @ColumnWidth(15)
    @ExcelProperty("用户名")
    private String userName;

    @ColumnWidth(15)
    @ExcelProperty("用户密码")
    private String password;

    @ColumnWidth(20)
    @ExcelProperty("手机号")
    private String phone;

    @ColumnWidth(15)
    @ExcelProperty("用户状态")
    private String del;

    @ColumnWidth(18)
    @ExcelProperty("注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
//
//    @ColumnWidth(15)
//    @ExcelProperty("卡包")
//    private List<Card> cards;
}
