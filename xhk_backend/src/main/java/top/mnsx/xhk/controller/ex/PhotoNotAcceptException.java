package top.mnsx.xhk.controller.ex;

import top.mnsx.xhk.service.ex.ServiceException;

/**
 * 未接收图片异常
 */
public class PhotoNotAcceptException extends ServiceException {
    public PhotoNotAcceptException() {
        super();
    }

    public PhotoNotAcceptException(String message) {
        super(message);
    }

    public PhotoNotAcceptException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoNotAcceptException(Throwable cause) {
        super(cause);
    }

    protected PhotoNotAcceptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
