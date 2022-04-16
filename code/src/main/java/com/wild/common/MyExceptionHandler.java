package com.wild.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 拦截自定义异常
     * 返回指定业务异常错误信息
     *
     * @param myException
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ResponseEntity myEx(MyException myException) {
        myException.printStackTrace();
        // 响应500 并且把错误对象返回
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(myException.getErrorResult());
    }

    /**
     * 拦截未知异常
     * 返回未知错误信息
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity ex(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResult.error());
    }
}