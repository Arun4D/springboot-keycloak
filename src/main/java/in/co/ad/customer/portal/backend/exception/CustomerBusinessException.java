package in.co.ad.customer.portal.backend.exception;

import in.co.ad.customer.portal.backend.util.ErrorMessage;

public class CustomerBusinessException extends Exception {
    public CustomerBusinessException() {
    }

    public CustomerBusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getDisplayMessage());
    }

    public CustomerBusinessException(String message) {
        super(message);
    }

    public CustomerBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerBusinessException(Throwable cause) {
        super(cause);
    }

    public CustomerBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
