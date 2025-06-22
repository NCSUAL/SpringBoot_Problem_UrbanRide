package lsh.security.constant.nested;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;

import lsh.security.exception.VehicleConverterException;

public enum VehicleType {
    COMPACT,
    SUV,
    EV;

    @JsonCreator
    public static VehicleType from(String source){
        return Arrays.stream(values()).filter(arg -> arg.toString().equalsIgnoreCase(source))
        .findAny()
        .orElseThrow(() ->  new VehicleConverterException(HttpStatus.BAD_REQUEST, "Vehicle type cast error"));
    }
}
