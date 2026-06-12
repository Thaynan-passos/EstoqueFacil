package com.EstoqueFacil.EstoqueFacil.service;


import exceptions.CampoPreenchimento;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ValidarRequisicao {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public static ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public static ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CampoPreenchimento.class)
    public static ResponseEntity<String> handleException(CampoPreenchimento ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
