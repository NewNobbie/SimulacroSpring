package riwi.simulacro_SpringBoot.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.simulacro_SpringBoot.domain.entities.Courses;
import riwi.simulacro_SpringBoot.domain.entities.User;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private UserResponse userId;
    private CourseResponse courseId;
    private LocalDate enrollmentDate;
}
