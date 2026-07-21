package io.github.arthur32p.Real_time_Leaderboard.controller.common;

import io.github.arthur32p.Real_time_Leaderboard.dto.ErrorResponse;
import io.github.arthur32p.Real_time_Leaderboard.dto.FieldErrorDto;
import io.github.arthur32p.Real_time_Leaderboard.exception.RecursoNaoEncontradoException;
import io.github.arthur32p.Real_time_Leaderboard.exception.UsuarioJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleConflict(UsuarioJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.conflict(ex.getMessage()));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.notFound(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldErrorDto> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> new FieldErrorDto(erro.getField(), erro.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.badRequest("Erro de validação nos campos informados.", erros));
    }
}
