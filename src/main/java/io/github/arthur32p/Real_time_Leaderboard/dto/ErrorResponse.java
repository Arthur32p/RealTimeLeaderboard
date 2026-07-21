package io.github.arthur32p.Real_time_Leaderboard.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(int status, String message, List<FieldErrorDto> errorList) {

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }

    public static ErrorResponse notFound(String message){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), message, List.of());
    }

    public static ErrorResponse badRequest(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponse badRequest(String message, List<FieldErrorDto> errors) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, errors);
    }

    public static ErrorResponse internalServerError(String message) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, List.of());
    }
}
