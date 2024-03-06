package org.globant.restaurant.exceptions.Handlers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

}