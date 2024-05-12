package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUC no puede estar en blanco")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 caracteres")
    private String ruc;

    @NotBlank(message = "La razón social no puede estar en blanco")
    private String razonSocial;

    @Pattern(regexp="\\d{9,10}", message="El teléfono debe tener entre 9 y 10 dígitos")
    private String telefono;

    @NotBlank(message = "El correo no puede estar en blanco")
    @Email(message = "El correo debe ser una dirección de correo electrónico válida")
    private String correo;

    @NotBlank(message = "La dirección no puede estar en blanco")
    private String direccion;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CurrentTimestamp
    private Date fecha_registro;
}
