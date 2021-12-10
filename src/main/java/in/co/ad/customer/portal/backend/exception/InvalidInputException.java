package in.co.ad.customer.portal.backend.exception;

import in.co.ad.customer.portal.backend.util.ErrorMessage;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
    }

    public InvalidInputException(ErrorMessage errorMessage) {
        super(errorMessage.getDisplayMessage());
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    public InvalidInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
