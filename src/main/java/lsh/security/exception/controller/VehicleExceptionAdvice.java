package lsh.security.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lsh.security.dto.ApiEntity;
import lsh.security.dto.ErrorDto;
import lsh.security.exception.VehicleConverterException;

@RestControllerAdvice
public class VehicleExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VehicleConverterException.class)
    public ApiEntity vehicleConverterException(VehicleConverterException vehicle){
        return ApiEntity.fail(ErrorDto.of(vehicle.getHttpStatus(), vehicle.getMessage()));
    }
}
