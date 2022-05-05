package top.mnsx.xhk.service.ex;


/**
 * 用户信息错误异常
 */
public class UserInfoWrongException extends ServiceException {
    public UserInfoWrongException() {
        super();
    }

    public UserInfoWrongException(String message) {
        super(message);
    }

    public UserInfoWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoWrongException(Throwable cause) {
        super(cause);
    }

    protected UserInfoWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
