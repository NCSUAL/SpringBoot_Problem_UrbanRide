package lsh.security.exception;

import org.springframework.http.HttpStatus;

public class VehicleConverterException extends CustomException{

    private final HttpStatus httpStatus;

    public VehicleConverterException(final HttpStatus httpStatus, final String message){
        super(httpStatus, message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
