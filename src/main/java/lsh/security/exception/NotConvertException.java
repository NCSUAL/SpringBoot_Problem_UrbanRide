package lsh.security.exception;

import org.springframework.http.HttpStatus;

public class NotConvertException extends CustomException {

    public NotConvertException(final HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
