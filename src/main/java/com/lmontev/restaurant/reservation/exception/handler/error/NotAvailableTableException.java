package com.lmontev.restaurant.reservation.exception.handler.error;

public class NotAvailableTableException extends RuntimeException{

    public NotAvailableTableException(String message) {
        super(message);
    }
}
