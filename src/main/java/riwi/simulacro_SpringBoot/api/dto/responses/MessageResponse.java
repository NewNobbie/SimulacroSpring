package riwi.simulacro_SpringBoot.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.simulacro_SpringBoot.domain.entities.Courses;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private UserResponse senderId;

    private UserResponse receiverId;

    private CourseResponse coursesId;

    private String messageContent;

    private LocalDate SentDate;
}
