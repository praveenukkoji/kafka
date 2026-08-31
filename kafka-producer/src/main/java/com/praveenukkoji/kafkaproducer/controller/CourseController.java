package com.praveenukkoji.kafkaproducer.controller;

import com.praveenukkoji.kafkaproducer.dto.CourseDTO;
import com.praveenukkoji.kafkaproducer.model.CreateCourseRequest;
import com.praveenukkoji.kafkaproducer.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("courseId") final String courseId) {
        log.info("---------- NEW GET COURSE BY ID REQUEST ----------");
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("---------- NEW GET ALL COURSES REQUEST ----------");
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody final CreateCourseRequest newCourse) {
        log.info("---------- NEW CREATE COURSE REQUEST ----------");
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(newCourse));
    }
}
