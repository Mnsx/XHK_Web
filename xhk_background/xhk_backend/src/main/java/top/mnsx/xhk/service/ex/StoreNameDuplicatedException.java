package top.mnsx.xhk.service.ex;

/**
 * 店名已被定义异常
 */
public class StoreNameDuplicatedException extends ServiceException {
    public StoreNameDuplicatedException() {
        super();
    }

    public StoreNameDuplicatedException(String message) {
        super(message);
    }

    public StoreNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected StoreNameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
