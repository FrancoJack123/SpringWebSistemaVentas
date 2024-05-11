package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código no puede estar en blanco")
    private String codigo;

    @NotBlank(message = "El documento no puede estar en blanco")
    private String documento;

    @NotNull(message = "La cantidad de producto no puede ser nula")
    @PositiveOrZero(message = "La cantidad de producto debe ser un número positivo o cero")
    private Integer cantidadProducto;

    @NotNull(message = "La cantidad total no puede ser nula")
    @PositiveOrZero(message = "La cantidad total debe ser un número positivo o cero")
    private Integer cantidadTotal;

    @NotNull(message = "El total costo no puede ser nulo")
    @PositiveOrZero(message = "El total costo debe ser un número positivo o cero")
    private Double totalCosto;

    @NotNull(message = "El importe recibido no puede ser nulo")
    @PositiveOrZero(message = "El importe recibido debe ser un número positivo o cero")
    private Double importeRecibido;

    @NotNull(message = "El importe de cambio no puede ser nulo")
    @PositiveOrZero(message = "El importe de cambio debe ser un número positivo o cero")
    private Double importeCambio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CurrentTimestamp
    private Date fecha_registro;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tienda tienda;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<DetalleVenta> detalleVentas;

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
        for (DetalleVenta detalleVenta : detalleVentas){
            detalleVenta.setVenta(this);
        }
    }
}
