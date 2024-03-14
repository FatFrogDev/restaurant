package org.globant.restaurant.controller;

import jakarta.persistence.EntityExistsException;
import org.globant.restaurant.exceptions.*;
import org.globant.restaurant.model.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles all exceptions thrown by the controllers.
 */
@Component
@RestControllerAdvice
public class ExceptionsHandlerController {

    private static final DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final String date = LocalDateTime.now().format(format);

    /**
     * Exceptions Handler for EntityAlreadyExists error.
     * This exception is thrown when the entity already exists in the database. <br>
     * The stats code returned will be CONFLICT (409).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<ErrorDTO> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.CONFLICT, date,"Validation error: " + e.getMessage(), EntityExistsException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Exceptions Handler EntityNotFoundException response error.
     * This exception is thrown when the entity already exists in the database. <br>
     * The stats code returned will be NOT_FOUND (404).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorDTO> handlerEntityNotFoundException(EntityNotFoundException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND, date,"Validation error: " + e.getMessage(), EntityNotFoundException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Exceptions Handler for EntityValidationException response error.
     * This exception is thrown when the entity that must be validated has at least one error of validation. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({EntityValidationException.class})
    public ResponseEntity<ErrorDTO> handleValidationException(EntityValidationException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), EntityValidationException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for Generic exceptions or errors.
     * This exception is thrown none of the exceptions is able to handle the occurred error so that all exceptions are covered.<br>
     * The stats code returned will be INTERNAL_SERVER_ERROR (500).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDTO> handleGenericException(Exception e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, date,"generic error: " + e.getMessage(), Exception.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Exceptions Handler for EntityUUIDInvalidFormatException exception.
     * This exception is thrown when the UUID format of an entity is not valid. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
    * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({EntityUUIDInvalidFormatException.class})
    public ResponseEntity<ErrorDTO> handleEntityUUIDInvalidFormatException(Exception e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), EntityUUIDInvalidFormatException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for ClientInvalidDocumentFormatException exception.
     * This exception is thrown when the Entity Client has a document format not valid. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({ClientInvalidDocumentFormatException.class})
    public ResponseEntity<ErrorDTO> handleClientInvalidDocumentFormatException(ClientInvalidDocumentFormatException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), ClientInvalidDocumentFormatException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for InvalidQueryArgsException exception.
     * This exception is thrown when the query arguments given are not valid. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({InvalidQueryArgsException.class})
    public ResponseEntity<ErrorDTO> handleInvalidQueryArgs(InvalidQueryArgsException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), InvalidQueryArgsException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for ClientHasDifferentDocumentException exception.
     * This exception is thrown when the received ClientDTO object has a different document that the already existent into the database, and it's not modifiable.<br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({ClientHasDifferentDocumentException.class})
    public ResponseEntity<ErrorDTO> handleClientHasDifferentDocumentException(ClientHasDifferentDocumentException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), ClientHasDifferentDocumentException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for ProductInvalidFantasyName exception.
     * This exception is thrown when a given product name given has an invalid format. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({ProductInvalidFantasyName.class})
    public ResponseEntity<ErrorDTO> handleProductInvalidFantasyName(ProductInvalidFantasyName e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), ProductInvalidFantasyName.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    /**
     * Exceptions Handler for EntityHasNoDifferentDataException exception.
     * This exception is thrown when given entity DTO has no different data compared to the already existent into the database. <br>
     * The stats code returned will be BAD_REQUEST (400).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({EntityHasNoDifferentDataException.class})
    public ResponseEntity<ErrorDTO> handleEntityHasNoDifferentDataException(EntityHasNoDifferentDataException e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST, date,"validation error: " + e.getMessage(), EntityHasNoDifferentDataException.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Exceptions Handler for OrderIsAlreadyDelivered exception.
     * This exception is thrown when given order is already setted to delivered.<br>
     * The stats code returned will be CONFLICT (409).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({OrderIsAlreadyDelivered.class})
    public ResponseEntity<ErrorDTO> handleEntityHasNoDifferentDataException(OrderIsAlreadyDelivered e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.CONFLICT, date,"validation error: " + e.getMessage(), OrderIsAlreadyDelivered.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Exceptions Handler for ProductIsUnavaliable exception.
     * This exception is thrown when a product is found but is unavaliable to set it in an order or when trying to create a product that has the same name that the unavaliable product. <br>
     * The stats code returned will be CONFLICT (409).
     * @param e Exception thrown
     * @return ResponseEntity object with a ErrorDTO which contains the error message.
     */
    @ExceptionHandler({ProductIsUnavaliable.class})
    public ResponseEntity<ErrorDTO> handleEntityHasNoDifferentDataException(ProductIsUnavaliable e) {
        ErrorDTO error = new ErrorDTO(HttpStatus.CONFLICT, date,"validation error: " + e.getMessage(), ProductIsUnavaliable.class.getSimpleName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
