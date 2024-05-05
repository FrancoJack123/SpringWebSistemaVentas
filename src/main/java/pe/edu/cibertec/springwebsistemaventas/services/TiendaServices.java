package pe.edu.cibertec.springwebsistemaventas.services;


import pe.edu.cibertec.springwebsistemaventas.entity.Categoria;
import pe.edu.cibertec.springwebsistemaventas.entity.Tienda;

import java.util.List;
import java.util.Optional;

public interface TiendaServices {
    List<Tienda> getAll();
    Tienda save(Tienda tienda);
    Optional<Tienda> getById(Long id);
    Boolean delete(Long id);
}
