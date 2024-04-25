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
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalCosto;
    private String tipoComprobante;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_registro;

    @ManyToOne
    private Tienda tienda;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Proveedor proveedor;

    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<DetalleCompra> detalleCompras;

    public void setDetalleCompras(Set<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
        for (DetalleCompra detalleCompra : detalleCompras){
            detalleCompra.setCompra(this);
        }
    }
}
