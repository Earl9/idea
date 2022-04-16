package com.wild.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResult {

    private String errCode;
    private String errMessage;

    public static ErrorResult error() {
        return ErrorResult.builder().errCode("999").errMessage("未知错误,请稍后重试").build();
    }

    public static ErrorResult nullError() {
        return ErrorResult.builder().errCode("50001").errMessage("数据为空").build();
    }
}
