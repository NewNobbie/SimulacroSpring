package riwi.simulacro_SpringBoot.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {

    @NotBlank(message = "EL titulo de la lesion es requerido")
    @Size(max = 100, message = "El titulo excede el limite de caracteres permitido")
    private String lessonTitle;

    @NotBlank(message = "EL contenido de la lesion es requerido")
    @Size(min = 10,message = "El contenido debe de tener mas de 10 caracteres")
    private String content;

    @NotNull(message = "El curso de esta lesion es requerido")
    private Long course;

}
