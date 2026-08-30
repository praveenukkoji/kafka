package com.praveenukkoji.kafkaproducer.exception;

public class InvalidCourseIdException extends RuntimeException {
    public InvalidCourseIdException(String message) {
        super(message);
    }
}
