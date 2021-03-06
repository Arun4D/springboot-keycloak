package in.co.ad.customer.portal.backend.util;

import in.co.ad.customer.portal.backend.exception.CustomerBusinessException;
import in.co.ad.customer.portal.backend.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.error("Invalid Input Exception: {} ",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpClientErrorException.Unauthorized.class })
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
        LOGGER.error("Unauthorized Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { CustomerBusinessException.class })
    public ResponseEntity<Object> handleBusinessException(CustomerBusinessException ex) {
        LOGGER.error("Business Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        LOGGER.error("Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ErrorMessage.SYSTEM_ERROR.getDisplayMessage() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
