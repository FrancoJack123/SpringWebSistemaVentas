package pe.edu.cibertec.springwebsistemaventas.services;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.springwebsistemaventas.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.entity.Producto;

import java.util.List;
import java.util.Optional;


public interface CategoriaService {
    List<Categoria> getAll();
    Categoria save(Categoria categoria);
    Optional<Categoria> getById(Long id);
    Boolean delete(Long id);
}
