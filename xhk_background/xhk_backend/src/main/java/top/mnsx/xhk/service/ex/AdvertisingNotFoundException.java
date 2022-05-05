package top.mnsx.xhk.service.ex;

/**
 * 广告不存在异常
 */
public class AdvertisingNotFoundException extends ServiceException{
    public AdvertisingNotFoundException() {
        super();
    }

    public AdvertisingNotFoundException(String message) {
        super(message);
    }

    public AdvertisingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdvertisingNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AdvertisingNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
