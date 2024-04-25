package pe.edu.cibertec.springwebsistemaventas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String ruc;
    private String ubicacion;
    private String telefono;
    private Boolean estado;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_registro;

    @OneToMany(mappedBy = "tienda", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<ProductoTienda> productoTiendas;

    public void setProductoTiendas(Set<ProductoTienda> productoTiendas) {
        this.productoTiendas = productoTiendas;
        for (ProductoTienda productoTienda : productoTiendas){
            productoTienda.setTienda(this);
        }
    }
}
