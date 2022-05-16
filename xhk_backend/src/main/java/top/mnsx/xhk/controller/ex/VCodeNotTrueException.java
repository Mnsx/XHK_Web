package top.mnsx.xhk.controller.ex;

import top.mnsx.xhk.controller.BaseController;
import top.mnsx.xhk.service.ex.ServiceException;

public class VCodeNotTrueException extends ServiceException {
    public VCodeNotTrueException() {
        super();
    }

    public VCodeNotTrueException(String message) {
        super(message);
    }

    public VCodeNotTrueException(String message, Throwable cause) {
        super(message, cause);
    }

    public VCodeNotTrueException(Throwable cause) {
        super(cause);
    }

    protected VCodeNotTrueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
