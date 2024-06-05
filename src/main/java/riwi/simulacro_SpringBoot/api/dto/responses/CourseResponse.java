package riwi.simulacro_SpringBoot.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.simulacro_SpringBoot.domain.entities.Lesson;

import java.util.List;

//6
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private String course_name;
    private String description;
    private UserResponse user;
    private List<LessonResponse> lessons;
}
