package com.praveenukkoji.kafkaproducer.controller;

import com.praveenukkoji.kafkaproducer.dto.CourseDTO;
import com.praveenukkoji.kafkaproducer.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("courseId") final String courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
    }
}
