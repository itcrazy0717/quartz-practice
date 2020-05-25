package com.quartz.practice.exception;

/**
 * @author: dengxin.chen
 * @date: 2020-05-18 22:04
 * @description: 业务异常
 */
public class BizException extends RuntimeException {

    public BizException(String errorMsg) {
        super(errorMsg);
    }
}
