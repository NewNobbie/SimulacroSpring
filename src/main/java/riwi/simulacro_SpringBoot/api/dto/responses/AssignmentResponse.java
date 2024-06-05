package riwi.simulacro_SpringBoot.api.dto.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//25
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponse {
    private String assignmentTitle;
    private String  description;
    private LocalDateTime dueDate;
    private LessonResponse lesson;
}
