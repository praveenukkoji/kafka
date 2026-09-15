package com.praveenukkoji.kafkaproducer.kafka.event.implementation;

import com.praveenukkoji.kafkaproducer.kafka.event.KafkaEvent;
import lombok.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreatedEvent implements KafkaEvent {
    private UUID courseId;
    private String courseName;
    private String courseTrainer;
    private String courseType;

    @Override
    public String getEventType() {
        return "COURSE_CREATED";
    }

    @Override
    public byte[] getPayload() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(outputStream);

            writeString(dataOutput, String.valueOf(courseId));
            writeString(dataOutput, courseName);
            writeString(dataOutput, courseTrainer);
            writeString(dataOutput, courseType);

            dataOutput.flush();

            return outputStream.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to serialize CourseCreatedEvent", e);
        }
    }

    private void writeString(DataOutputStream output, String value) throws IOException {
        if (value == null) {
            output.writeInt(-1);
            return;
        }

        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        output.writeInt(bytes.length);
        output.write(bytes);
    }
}
