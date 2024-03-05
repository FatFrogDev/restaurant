package org.globant.restaurant.exceptions.Handlers;

import org.globant.restaurant.exceptions.Client.*;
import org.globant.restaurant.exceptions.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ClientExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientDocumentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ResponseEntity<ErrorObject> clientDocumentAlreadyExists() {
        ErrorObject message = new ErrorObject(HttpStatus.NOT_FOUND, LocalDate.now(),"Client with specified document already exists.", ClientDocumentAlreadyExistsException.class.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ClientDocumentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ResponseEntity<ErrorObject> clientDataInvalidOrIncomplete() {
        ErrorObject message = new ErrorObject(HttpStatus.BAD_REQUEST, LocalDate.now(),"Client data invalid or incomplete.", ClientDataInvalidOrIncompleteException.class.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ErrorObject> clientNotFoundException() {
        ErrorObject message = new ErrorObject(HttpStatus.NOT_FOUND, LocalDate.now(),"Client not found or does not exists.", ClientNotFoundException.class.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ClientNoDifferentDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ResponseEntity<ErrorObject> noDifferentDataException() {
        ErrorObject message = new ErrorObject(HttpStatus.CONFLICT, LocalDate.now(),"Data to update must be different that the actual data.", ClientNoDifferentDataException.class.getName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(InvalidDocumentFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorObject> invalidDocumentFormatException() {
        ErrorObject message = new ErrorObject(HttpStatus.BAD_REQUEST, LocalDate.now(),"Invalid Document format given.", InvalidDocumentFormatException.class.getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
