package com.imcode.common.exception;

/**
 * 自定义业务异常
 */

public class BizException extends Exception {
    public BizException(String msg) {
        super(msg);
    }
}
