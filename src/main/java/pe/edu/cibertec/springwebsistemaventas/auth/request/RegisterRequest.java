package pe.edu.cibertec.springwebsistemaventas.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.springwebsistemaventas.validation.email.usuario.EmailValidator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "El campo es requerido")
    private String nombres;
    @NotBlank(message = "El campo es requerido")
    private String apellidos;
    @NotBlank(message = "El campo es requerido")
    @EmailValidator
    private String email;
    @NotBlank(message = "El campo es requerido")
    private String password;
    private String rol;
}