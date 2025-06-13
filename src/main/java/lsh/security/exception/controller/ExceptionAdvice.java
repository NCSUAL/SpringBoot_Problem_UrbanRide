package lsh.security.exception.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.ErrorDto;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiEntity constraintViolationException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getFieldErrors() .stream() .map(error -> "[" + error.getField() + "] " + error.getDefaultMessage()).findFirst().get();
        return ApiEntity.fail(ErrorDto.of("400", message));
    }
}
