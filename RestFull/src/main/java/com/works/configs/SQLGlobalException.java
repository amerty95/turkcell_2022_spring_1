package com.works.configs;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@RestControllerAdvice
public class SQLGlobalException extends ConstraintViolationException {

    static String message = "Global Message";
    public SQLGlobalException( Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
        System.out.println("ConstraintViolationException Call");
    }


}
