package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getAll();
    Producto save(Producto producto);
    Optional<Producto> getById(Long id);
    Boolean delete(Long id);
}
