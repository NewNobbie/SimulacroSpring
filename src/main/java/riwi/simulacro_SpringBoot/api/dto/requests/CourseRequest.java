package riwi.simulacro_SpringBoot.api.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 5
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    @NotNull(message = "El nombre del curso es requerido")
    @Size(min = 1, max = 100, message = "El nombre del curso debe tener entre 1 y 30 caracteres")
    private String course_name;

    @NotNull(message = "La descripcion del curso es requerida")
    @Size(min = 1, max = 100, message = "La descripcion del curso debe tener entre 1 y 100 caracteres")
    private String description;

    @NotNull(message = "Debes seleccionar un usuario")
    private Long user;
}
