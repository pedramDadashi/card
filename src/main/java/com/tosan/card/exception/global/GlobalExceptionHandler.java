package com.tosan.card.exception.global;

import com.tosan.card.exception.dto.ExceptionInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionInfoDTO> invalidInputExceptionHandler(RuntimeException re) {
        return ResponseEntity.badRequest().body(
                new ExceptionInfoDTO(re.getClass().getSimpleName(), re.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionInfoDTO> invalidInputExceptionHandler(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                new ExceptionInfoDTO("INVALID_INPUT", ex.getMessage()));
    }

    @ExceptionHandler(TimeoutException.class)
    ResponseEntity<ExceptionInfoDTO> invalidInputExceptionHandler(TimeoutException ex) {
        return ResponseEntity.badRequest().body(
                new ExceptionInfoDTO(ex.getClass().getSimpleName(), ex.getMessage()));
    }


}


