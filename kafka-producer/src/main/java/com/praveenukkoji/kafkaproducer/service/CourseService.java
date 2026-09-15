package com.praveenukkoji.kafkaproducer.service;

import com.praveenukkoji.kafkaproducer.dto.CourseDTO;
import com.praveenukkoji.kafkaproducer.exception.CourseCreationException;
import com.praveenukkoji.kafkaproducer.exception.CourseNotFoundException;
import com.praveenukkoji.kafkaproducer.exception.InvalidCourseIdException;
import com.praveenukkoji.kafkaproducer.entity.Course;
import com.praveenukkoji.kafkaproducer.kafka.event.implementation.CourseCreatedEvent;
import com.praveenukkoji.kafkaproducer.kafka.service.KafkaService;
import com.praveenukkoji.kafkaproducer.model.CreateCourseRequest;
import com.praveenukkoji.kafkaproducer.repository.CourseRepository;
import com.praveenukkoji.kafkaproducer.utils.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final Utility utility;

    private final KafkaService kafkaService;

    private final ModelMapper modelMapper;

    public CourseDTO getCourseById(final String courseId) {
        log.info("getting course by id: {}", courseId);

        if(!utility.validateCourseId(courseId)) {
            log.info("invalid course id: {}", courseId);
            throw new InvalidCourseIdException("invalid course id");
        }

        UUID id = UUID.fromString(courseId);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("course by id : {} not found", courseId);
                    return new CourseNotFoundException("course not found");
                });

        log.info("course found by id: {}", courseId);
        return modelMapper.map(course, CourseDTO.class);
    }

    public List<CourseDTO> getAll() {
        log.info("getting all courses");

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .toList();
    }

    public CourseDTO createCourse(CreateCourseRequest newCourse) {
        Course course = modelMapper.map(newCourse, Course.class);

        // set from auth token
         course.setCreatedBy("praveenukkoji");
         course.setModifiedBy("praveenukkoji");

        try {
            course = courseRepository.saveAndFlush(course);
            log.info("course created: {}", course);

            CourseCreatedEvent courseCreatedEvent =  modelMapper.map(course, CourseCreatedEvent.class);

            log.info("sending event: {}", courseCreatedEvent);
            kafkaService.send(courseCreatedEvent);
        }
        catch(Exception e) {
            log.error("course creation failed, error -> {}", e.getMessage());
            throw new CourseCreationException("course creation failed");
        }

        return modelMapper.map(course, CourseDTO.class);
    }
}
