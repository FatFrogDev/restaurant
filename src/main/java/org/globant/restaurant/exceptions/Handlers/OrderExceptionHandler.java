package org.globant.restaurant.exceptions.Handlers;

import org.globant.restaurant.exceptions.ErrorObject;
import org.globant.restaurant.exceptions.Product.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

}