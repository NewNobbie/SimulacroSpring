package riwi.simulacro_SpringBoot.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {

    private String content;
    private LocalDate submissionDate;
    private Double grade;
    private UserResponse user_id;
    private AssignmentResponse assignment_id;
}
