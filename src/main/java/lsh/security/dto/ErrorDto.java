package lsh.security.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDto {
    private final String ErrorCode;
    private final String ErrorMessage;

    public static void of(HttpStatus httpStatus, final Exception exception){

    }
}
