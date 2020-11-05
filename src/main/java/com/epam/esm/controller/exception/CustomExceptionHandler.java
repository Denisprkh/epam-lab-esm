package com.epam.esm.controller.exception;

import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.util.ResourceBundleErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler {

    private MessageSource messageSource;

    public CustomExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException e, Locale locale) {
        String errorMessage = messageSource.getMessage(e.getBindingResult().getFieldError().getDefaultMessage(),
                new Object[]{}, locale);
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e,
                                                                                  Locale locale) {
        String errorMessage = String.format(messageSource.getMessage(e.getMessage(), new Object[]{},
                locale), e.getResourceId());
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e, Locale locale) {
        String errorMessage = messageSource.getMessage(e.getMessage(), new Object[]{}, locale);
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e, Locale locale) {
//        String errorMessage = messageSource.getMessage(ResourceBundleErrorMessage.INTERNAL_SERVER_ERROR, new Object[]{},
//                locale);
//        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
