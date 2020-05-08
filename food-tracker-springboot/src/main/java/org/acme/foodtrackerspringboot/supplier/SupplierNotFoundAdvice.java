package org.acme.foodtrackerspringboot.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SupplierNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SupplierNotfoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String manufacturerNotFoundHandler(SupplierNotfoundException ex) {
        return ex.getMessage();
    }
}
