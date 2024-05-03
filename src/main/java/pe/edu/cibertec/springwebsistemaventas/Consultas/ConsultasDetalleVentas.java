package pe.edu.cibertec.springwebsistemaventas.Consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.cibertec.springwebsistemaventas.entity.DetalleVenta;

public class ConsultasDetalleVentas {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdsistemaventas");
        EntityManager em = emf.createEntityManager();

        DetalleVenta detalleVenta = em.find(DetalleVenta.class,"1");

        System.out.println(detalleVenta);
    }
}
