package pe.edu.cibertec.springwebsistemaventas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String documento;
    private Integer cantidadProducto;
    private Integer cantidadTotal;
    private Double totalCosto;
    private Double importeRecibido;
    private Double importeCambio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_registro;

    @ManyToOne
    private Tienda tienda;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<DetalleVenta> detalleVentas;

    public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
        for (DetalleVenta detalleVenta : detalleVentas){
            detalleVenta.setVenta(this);
        }
    }
}
