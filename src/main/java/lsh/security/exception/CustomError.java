package lsh.security.exception;

import org.springframework.http.HttpStatus;

public class CustomError extends RuntimeException{
    private final HttpStatus httpStatus;

    public CustomError(final HttpStatus httpStatus ,final String message){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
