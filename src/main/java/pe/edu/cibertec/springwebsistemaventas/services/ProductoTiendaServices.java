package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.entity.ProductoTienda;

import java.util.List;
import java.util.Optional;

public interface ProductoTiendaServices {
    List<ProductoTienda> getAll();
    ProductoTienda save(ProductoTienda productoTienda);
    Optional<ProductoTienda> getById(Long id);
    Boolean delete(Long id);
}
