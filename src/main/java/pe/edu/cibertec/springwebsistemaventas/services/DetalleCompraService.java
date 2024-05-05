package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.entity.DetalleCompra;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraService {
    List<DetalleCompra> getAll();
    DetalleCompra save(DetalleCompra detalleCompra);
    Optional<DetalleCompra> getById(Long id);
    Boolean delete(Long id);
}
