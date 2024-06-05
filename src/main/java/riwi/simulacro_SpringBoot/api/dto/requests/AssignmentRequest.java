package riwi.simulacro_SpringBoot.api.dto.requests;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 24
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentRequest {

    @NotBlank(message = "EL titulo del assignement es requerido")
    @Size(max = 100, message = "El titulo excede el limite de caracteres permitido")
    private String assignmentTitle;
    @NotBlank(message = "EL titulo del description es requerido")
    private String description;
    @NotNull(message = "Debes seleccionar una leccion")
    private Long lesson;

}
