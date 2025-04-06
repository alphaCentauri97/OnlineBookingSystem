package com.example.OnlineBookingSystem.Online.Booking.System.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
