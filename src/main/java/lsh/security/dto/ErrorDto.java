package lsh.security.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDto {
    private final String errorCode;
    private final String errorMessage;

    public static ErrorDto of(final String errorCode, final Exception exception){
        return new ErrorDto(errorCode, exception.getMessage());
    }
}
