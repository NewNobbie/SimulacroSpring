package riwi.simulacro_SpringBoot.api.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequest {
    @NotNull(message = "Se requiere el curso ")
    private Long course;
    @NotNull(message = "Se requiere el usuario")
    private Long user;
}
