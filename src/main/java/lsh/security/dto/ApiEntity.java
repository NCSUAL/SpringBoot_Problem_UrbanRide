package lsh.security.dto;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ApiEntity<T>(
    @JsonIgnore
    HttpStatus status,

    Optional<T> data,

    boolean success,

    Optional<Exception> exception
    
) {
    public static <T> ApiEntity<T> ok(T data){
        return new ApiEntity<>(HttpStatus.OK, Optional.of(data), true, Optional.empty());
    }
}
