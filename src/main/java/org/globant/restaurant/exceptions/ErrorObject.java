package org.globant.restaurant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private HttpStatus code;
    private LocalDate timestamp;
    private String description;
    private String exception;
}
