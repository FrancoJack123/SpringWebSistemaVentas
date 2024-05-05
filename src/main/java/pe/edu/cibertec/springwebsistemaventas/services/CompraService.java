package pe.edu.cibertec.springwebsistemaventas.services;

import pe.edu.cibertec.springwebsistemaventas.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    List<Compra> getAll();
    Compra save(Compra compra);
    Optional<Compra> getById(Long id);
    Boolean delete(Long id);
}
