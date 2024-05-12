package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

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
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad no puede ser nula")
    @PositiveOrZero(message = "La cantidad debe ser un número positivo o cero")
    private Integer cantidad;

    @NotNull(message = "El precio unitario de compra no puede ser nulo")
    @PositiveOrZero(message = "El precio unitario de compra debe ser un número positivo o cero")
    private Double precioUnitarioCompra;

    @NotNull(message = "El precio unitario de venta no puede ser nulo")
    @PositiveOrZero(message = "El precio unitario de venta debe ser un número positivo o cero")
    private Double precioUnitarioVenta;

    @NotNull(message = "El total costo no puede ser nulo")
    @PositiveOrZero(message = "El total costo debe ser un número positivo o cero")
    private Double totalCosto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CurrentTimestamp
    private Date fecha_registro;

    @NotNull
    @ManyToOne
    private Compra compra;

    @NotNull
    @ManyToOne
    private Producto producto;

}
