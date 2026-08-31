package com.praveenukkoji.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest {
    private String courseName;
    private String courseTrainer;
    private String courseType;
}
