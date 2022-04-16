package com.wild.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalVo{

    //状态标题  全部  待审核 已通过 已拒绝
    private String title;
    //状态值   1   2   3   4
    private String code;
    //数量
    private Integer value;
}
