package pe.edu.cibertec.springwebsistemaventas.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}