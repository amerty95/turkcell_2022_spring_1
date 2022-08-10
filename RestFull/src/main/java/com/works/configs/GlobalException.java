package com.works.configs;

import com.works.utils.ERest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, false);
        hm.put(ERest.errors, fncValidControl(ex));
        return new ResponseEntity<>(hm, HttpStatus.BAD_REQUEST);
    }



    private List<Map> fncValidControl(MethodArgumentNotValidException ex) {
        List<Map> ls = new ArrayList<>();
        List<FieldError> list = ex.getFieldErrors();
        for( FieldError item : list ) {
            Map<String, String > hm = new HashMap<>();
            String field = item.getField();
            String message = item.getDefaultMessage();
            hm.put(field, message);
            ls.add(hm);
        }
        return ls;
    }
}
