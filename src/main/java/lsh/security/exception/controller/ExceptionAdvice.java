package lsh.security.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.ErrorDto;
import lsh.security.exception.NotConvertException;
import lsh.security.exception.NotFoundEntityException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiEntity constraintViolationException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).findFirst().get();
        return ApiEntity.fail(ErrorDto.of(HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiEntity<?> notFoundEntityException(NotFoundEntityException exception){
        return ApiEntity.fail(ErrorDto.of(exception.getHttpStatus(), exception.getMessage()));
    }

    @ExceptionHandler(NotConvertException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiEntity<?> notConverterException(NotConvertException exception){
        return ApiEntity.fail(ErrorDto.of(exception.getHttpStatus(), exception.getMessage()));
    }
}
