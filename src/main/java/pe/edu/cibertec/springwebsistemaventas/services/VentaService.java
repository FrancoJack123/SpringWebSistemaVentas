package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> getAll();
    Venta save(Venta venta);
    Optional<Venta> getById(Long id);
    Boolean delete(Long id);
}
