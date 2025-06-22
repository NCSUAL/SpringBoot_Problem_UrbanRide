package lsh.security.exception;

import org.springframework.http.HttpStatus;

public class CityTypeConverterException extends CustomException{

    public CityTypeConverterException(final HttpStatus errorCode ,final String message){
        super(errorCode,message);
    }

}
