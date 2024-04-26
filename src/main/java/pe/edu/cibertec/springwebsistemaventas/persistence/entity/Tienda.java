package pe.edu.cibertec.springwebsistemaventas.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "El RUC no puede estar en blanco")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 caracteres")
    @Column(unique = true)
    private String ruc;

    @NotBlank(message = "La ubicacion no puede estar en blanco")
    private String ubicacion;

    @Pattern(regexp="\\d{9,10}", message="El teléfono debe tener entre 9 y 10 dígitos")
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
