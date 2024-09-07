package com.project.ticketadministrations.ControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.ticketadministrations.DTO.ResponseStructure;
import com.project.ticketadministrations.Exception.IDnotFoundException;
@RestControllerAdvice
public class TicketAdministrationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IDnotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> IDnotFoundExceptionHandler(IDnotFoundException e) {
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setData("Invalid Id");
        res.setMessage(e.getMessage());
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);

    }

}