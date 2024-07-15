package io.rachidassouani.productservice.exception;


import java.time.LocalDateTime;
import java.util.Set;

public record ApiError(
        String path,
        String message,
        Set<String> errors,
        int statusCode,
        LocalDateTime exceptionTime) {
}

