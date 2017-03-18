package com.whb.sys.org.model;

/**
 * Created by WHB on 2016/7/3.
 */
@SuppressWarnings("serial")
public class SysException extends RuntimeException {
    public SysException() {
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

}
