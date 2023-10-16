package com.tosan.card.exception.global;

import com.tosan.card.exception.dto.ExceptionInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionInfoDTO> runTimeExceptionHandler(RuntimeException re) {
        return ResponseEntity.badRequest().body(
                new ExceptionInfoDTO(re.getClass().getSimpleName(), re.getMessage()));
    }

}


