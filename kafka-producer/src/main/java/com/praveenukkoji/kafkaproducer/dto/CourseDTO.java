package com.praveenukkoji.kafkaproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    UUID courseId;
    String courseName;
    String courseTrainer;
    String courseType;
}
