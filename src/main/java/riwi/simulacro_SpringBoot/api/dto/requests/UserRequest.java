package riwi.simulacro_SpringBoot.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import riwi.simulacro_SpringBoot.util.enums.EnumRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "El Usuarios es requerido")
    private String username;
    @NotBlank(message = "El email es requerido")
    @Size(min=5,max=100,message = "El email debe tener entre 5 y 100 caracteres")
    private String email;
    @NotBlank(message = "El password es requerido")
    private String password;
    @NotBlank(message = "El nombre completo es requerido")
    private String fullName;
    @NotNull(message = "El rol es requerido")
    private EnumRole role;

}
