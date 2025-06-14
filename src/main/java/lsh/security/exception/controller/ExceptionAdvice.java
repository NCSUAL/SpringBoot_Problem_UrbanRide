package lsh.security.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.ErrorDto;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiEntity constraintViolationException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).findFirst().get();
        return ApiEntity.fail(ErrorDto.of(HttpStatus.BAD_REQUEST, message));
    }
}
