package top.mnsx.xhk.controller.ex;

import top.mnsx.xhk.service.ex.ServiceException;

/**
 * 文件大小超过最大限制
 */
public class FileSizeMoreMaxSizeException extends ServiceException {
    public FileSizeMoreMaxSizeException() {
        super();
    }

    public FileSizeMoreMaxSizeException(String message) {
        super(message);
    }

    public FileSizeMoreMaxSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeMoreMaxSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeMoreMaxSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
