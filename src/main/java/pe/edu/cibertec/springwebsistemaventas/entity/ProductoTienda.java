package pe.edu.cibertec.springwebsistemaventas.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ProductoTienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double precioUnidadCompra;
    private Double precioUnidadVenta;
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
