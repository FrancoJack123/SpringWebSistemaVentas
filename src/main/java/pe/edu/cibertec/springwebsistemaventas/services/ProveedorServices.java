package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.entity.Producto;
import pe.edu.cibertec.springwebsistemaventas.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorServices {
    List<Proveedor> getAll();
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> getById(Long id);
    Boolean delete(Long id);
}
