package com.yannfigueiredo.atsresumegenerator.controllers.exceptions;

import com.yannfigueiredo.atsresumegenerator.services.exceptions.ObjectNotFoundException;
import com.yannfigueiredo.atsresumegenerator.services.exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(
            ObjectNotFoundException e,
            HttpServletRequest request
    ) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;

        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setMessage(e.getMessage());
        error.setStatus(status.value());
        error.setError("Resource not found!");

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(
        DatabaseException e,
        HttpServletRequest request
    ) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setMessage(e.getMessage());
        error.setStatus(status.value());
        error.setError("Database exception");

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> badRequestException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        error.setStatus(status.value());
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setMessage(e.getMessage());
        error.setError("Validation Error");

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(
        DataIntegrityViolationException e,
        HttpServletRequest request
    ) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.CONFLICT;

        error.setStatus(status.value());
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());
        error.setMessage(e.getMessage());
        error.setError("Data integrity violation error");

        return ResponseEntity.status(status).body(error);
    }
}
