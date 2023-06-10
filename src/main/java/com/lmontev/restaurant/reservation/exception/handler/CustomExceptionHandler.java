package com.lmontev.restaurant.reservation.exception.handler;

import com.lmontev.restaurant.reservation.exception.handler.error.BadRequestCustomException;
import com.lmontev.restaurant.reservation.exception.handler.dto.ErrorResponseDTO;
import com.lmontev.restaurant.reservation.exception.handler.error.NotAvailableTableException;
import com.lmontev.restaurant.reservation.exception.handler.error.NotFoundCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleNoRecordFoundException(NotFoundCustomException ex) {
        return getErrorResponse(ex);
    }

    @ExceptionHandler(BadRequestCustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleBadRequestException(BadRequestCustomException ex){
        return getErrorResponse(ex);
    }

    @ExceptionHandler(NotAvailableTableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponseDTO handleNotAvailableTableException(NotAvailableTableException ex){
        return getErrorResponse(ex);
    }

    private static ErrorResponseDTO getErrorResponse(RuntimeException ex) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(ex.getMessage());
        return errorResponseDTO;
    }

}
