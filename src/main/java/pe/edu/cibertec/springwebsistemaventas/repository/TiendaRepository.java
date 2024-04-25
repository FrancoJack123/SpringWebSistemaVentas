package pe.edu.cibertec.springwebsistemaventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.springwebsistemaventas.entity.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {
}