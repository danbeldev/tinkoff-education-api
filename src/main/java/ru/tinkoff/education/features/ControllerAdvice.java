package ru.tinkoff.education.features;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.education.common.exceptions.BadRequestException;
import ru.tinkoff.education.common.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(final ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage(), "resource_not_found_error");
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleDataIntegrityViolation(final DataIntegrityViolationException e) {
        return new ExceptionBody(e.getMessage(), "data_integrity_violation_error");
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBadRequestException(final BadRequestException e) {
        return new ExceptionBody(e.getMessage(), "bad_request_error");
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleException(final Exception e) {
        e.printStackTrace();;
        return new ExceptionBody(e.getMessage(), "internal_error");
    }
}