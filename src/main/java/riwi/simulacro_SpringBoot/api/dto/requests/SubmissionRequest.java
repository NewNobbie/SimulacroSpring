package riwi.simulacro_SpringBoot.api.dto.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    @Size(min = 10,message = "El contenido debe de tener un mínimo de 10 caracteres")
    @NotBlank(message = "El contenido es requerido")
    private String content;
    @DecimalMin(value = "0",message = "La calificación no puede ser negativa")
    @NotNull(message = "la calificación es requerida")
    private Double grade;
    @NotNull(message = "El usuario es requerida")
    private Long user_id;
    @NotNull(message = "la asignación es requerida")
    private Long assignment_id;
}
