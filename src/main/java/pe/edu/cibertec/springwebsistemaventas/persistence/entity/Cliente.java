package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo es requerido")
    private String nombres;
    @NotBlank(message = "El campo es requerido")
    private String apellidos;
    @NotBlank(message = "El campo es requerido")
    private String direccion;
    @NotBlank(message = "El campo es requerido")
    private String telefono;
    @NotBlank(message = "El campo es requerido")
    private String dni;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CurrentTimestamp
    private Date fecha_registro;
}
