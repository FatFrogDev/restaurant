package org.globant.restaurant.exceptions.Handlers;

import org.globant.restaurant.exceptions.ErrorObject;
import org.globant.restaurant.exceptions.Product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    private final DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final String date = LocalDateTime.now().format(format);
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorObject> productNotFoundException() {
        ErrorObject message = new ErrorObject(HttpStatus.NOT_FOUND, date, "Product not found or does not exists.", ProductNotFoundException.class.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ProductDataInvalidOrIncompleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorObject> productDataInvalidOrIncompleteException() {
        ErrorObject message = new ErrorObject(HttpStatus.BAD_REQUEST, date, "Product data invalid or incomplete.", ProductDataInvalidOrIncompleteException.class.getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ProductNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorObject> productNameAlreadyExistsException() {
        ErrorObject message = new ErrorObject(HttpStatus.CONFLICT, date, "Product name already exists.", ProductNameAlreadyExistsException.class.getName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(ProductUUIDInvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorObject> productUUIDInvalidFormatException() {
        ErrorObject message = new ErrorObject(HttpStatus.BAD_REQUEST, date, "Product has an invalid UUID format.", ProductUUIDInvalidFormatException.class.getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ProductNoDifferentDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorObject> productNoDifferentDataException() {
        ErrorObject message = new ErrorObject(HttpStatus.BAD_REQUEST, date, "Product must have different data to be modified.", ProductNoDifferentDataException.class.getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
