package lsh.security.exception;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends CustomException{

    public NotFoundEntityException(final HttpStatus httpStatus,final String message){
        super(httpStatus, message);
    }
}
