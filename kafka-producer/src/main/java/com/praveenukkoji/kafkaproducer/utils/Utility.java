package com.praveenukkoji.kafkaproducer.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utility {
    public boolean validateCourseId(final String courseId) {
        if(courseId == null || courseId.isEmpty()) {
            return false;
        }

        try {
            UUID.fromString(courseId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
