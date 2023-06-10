package com.lmontev.restaurant.reservation.exception.handler.error;

public class NotFoundCustomException extends RuntimeException{

    public NotFoundCustomException(String message) {
        super(message);
    }
}
