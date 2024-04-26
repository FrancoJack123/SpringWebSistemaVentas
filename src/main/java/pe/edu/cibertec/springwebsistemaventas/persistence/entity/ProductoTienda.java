package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ProductoTienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El precio unidad compra no puede ser nulo")
    @PositiveOrZero(message = "El precio unidad compra debe ser un número positivo o cero")
    private Double precioUnidadCompra;

    @NotNull(message = "El precio unidad venta no puede ser nulo")
    @PositiveOrZero(message = "El precio unidad venta debe ser un número positivo o cero")
    private Double precioUnidadVenta;

    @NotNull(message = "El stock no puede ser nulo")
    @PositiveOrZero(message = "El stock debe ser un número positivo o cero")
    private Integer stock;

    private Boolean estado;

    private Boolean iniciado;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_registro;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Tienda tienda;
}
