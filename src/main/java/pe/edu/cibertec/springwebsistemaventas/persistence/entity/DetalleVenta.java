package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad no puede ser nula")
    @PositiveOrZero(message = "La cantidad debe ser un número positivo o cero")
    private Integer cantidad;

    @NotNull(message = "El precio unidad no puede ser nulo")
    @PositiveOrZero(message = "El precio unidad debe ser un número positivo o cero")
    private Double precioUnidad;

    @NotNull(message = "El importe total no puede ser nulo")
    @PositiveOrZero(message = "El importe total debe ser un número positivo o cero")
    private Double importeTotal;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CurrentTimestamp
    private Date fecha_registro;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Venta venta;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Producto producto;
}
