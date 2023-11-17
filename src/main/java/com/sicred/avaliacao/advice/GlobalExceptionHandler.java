package com.sicred.avaliacao.advice;

import com.sicred.avaliacao.dto.ApiErrorDTO;
import com.sicred.avaliacao.exception.ValidaPautaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidaPautaException.class)
    public ResponseEntity<ApiErrorDTO> handleValidaProjetoException(HttpServletRequest request, ValidaPautaException ex) {
        ApiErrorDTO response = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorDTO>> handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException ex) {
        List<ApiErrorDTO> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ApiErrorDTO(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        fieldError.getDefaultMessage(),
                        request.getRequestURI()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDTO> handleJSONParseError(HttpServletRequest request, HttpMessageNotReadableException ex) {
        ApiErrorDTO response = new ApiErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Erro na conversão do JSON: " + ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
