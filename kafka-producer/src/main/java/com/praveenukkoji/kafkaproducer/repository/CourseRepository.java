package com.praveenukkoji.kafkaproducer.repository;

import com.praveenukkoji.kafkaproducer.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
}
