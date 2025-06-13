package lsh.security.exception.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lsh.security.dto.ApiEntity;
import lsh.security.dto.ErrorDto;
import lsh.security.exception.CityTypeConverterException;

@RestControllerAdvice
public class BranchExceptionAdvice {

    @ExceptionHandler(CityTypeConverterException.class)
    public ApiEntity<?> cityTypeConverterException(CityTypeConverterException exception){
        return ApiEntity.fail(ErrorDto.of(exception.getErrorCode(), exception.getMessage()));
    }

}
