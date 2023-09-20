package com.si.meAjude.exceptions.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TradadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){
        List<FieldError> errorFieldList = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errorFieldList.stream().map(ErrorValidationDTO::new).toList());
    }

    private record ErrorValidationDTO(String campo, String mensagem){
        public ErrorValidationDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
