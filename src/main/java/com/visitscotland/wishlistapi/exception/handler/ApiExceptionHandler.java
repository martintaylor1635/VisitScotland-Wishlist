package com.visitscotland.wishlistapi.exception.handler;

import com.visitscotland.wishlistapi.exception.base.AlreadyExistsException;
import com.visitscotland.wishlistapi.exception.base.DisallowedOperationException;
import com.visitscotland.wishlistapi.exception.base.NotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    private ProblemDetail validationExceptionHandler(ConstraintViolationException exception) {
        final String validationErrors = exception.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));

        return ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, validationErrors);
    }

    @ExceptionHandler(DisallowedOperationException.class)
    private ProblemDetail validationExceptionHandler(DisallowedOperationException exception) {
        return ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ProblemDetail notFoundExceptionHandler(NotFoundException exception) {
        return ProblemDetail
            .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    private ProblemDetail alreadyExistsExceptionHandler(AlreadyExistsException exception) {
        return ProblemDetail
            .forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }
}