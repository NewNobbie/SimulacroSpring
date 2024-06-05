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
public class MessageRequest {
    @NotNull(message = "Se requiere el usuario que envía el mensaje")
    private Long senderId;
    @NotNull(message = "Se requiere el usuario que resive el mensaje")
    private Long receiverId;
    @NotNull(message = "Se requiere el curso")
    private Long coursesId;
    @Size(min = 10, message = "El contenido del mensaje debe ser tener mínimo 10 caracteres")
    @NotBlank(message = "El contenido del mensaje es requerido")
    private String messageContent;


}
