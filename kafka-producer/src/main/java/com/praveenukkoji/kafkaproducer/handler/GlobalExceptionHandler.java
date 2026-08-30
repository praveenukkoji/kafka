package com.praveenukkoji.kafkaproducer.handler;

import com.praveenukkoji.kafkaproducer.exception.CourseNotFoundException;
import com.praveenukkoji.kafkaproducer.exception.InvalidCourseIdException;
import com.praveenukkoji.kafkaproducer.handler.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<?> handleException(CourseNotFoundException exception) {
        log.error("CourseNotFoundException - {}", exception.getMessage());

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidCourseIdException.class)
    public ResponseEntity<?> handleException(InvalidCourseIdException exception) {
        log.error("InvalidCourseIdException - {}", exception.getMessage());

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
