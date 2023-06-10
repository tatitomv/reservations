package com.lmontev.restaurant.reservation.exception.handler.error;

public class BadRequestCustomException extends RuntimeException{

    public BadRequestCustomException(String message) {
        super(message);
    }
}
