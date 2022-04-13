package com.wild.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常类
 */

@Data
@NoArgsConstructor
public class MyException extends RuntimeException {
    private ErrorResult errorResult;

    public MyException(ErrorResult errorResult) {
        super(errorResult.getErrMessage());
        this.errorResult = errorResult;
    }
}