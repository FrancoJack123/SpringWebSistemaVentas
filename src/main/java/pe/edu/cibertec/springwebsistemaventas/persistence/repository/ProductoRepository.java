package pe.edu.cibertec.springwebsistemaventas.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.springwebsistemaventas.persistence.entity.Producto;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("select p from Producto p where upper(p.nombre) like upper(concat('%', ?1, '%'))")
    List<Producto> findByNombreContainsIgnoreCase(String nombre);

    @Query("select p from Producto p where p.fecha_registro between ?1 and ?2")
    List<Producto> findByFecha_registroBetween(Date fecha_registroStart, Date fecha_registroEnd);
}