package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    List<DetalleVenta> getAll();
    DetalleVenta save(DetalleVenta detalleVenta);
    Optional<DetalleVenta> getById(Long id);
    Boolean delete(Long id);
}
