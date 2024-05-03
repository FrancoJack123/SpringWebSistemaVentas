package pe.edu.cibertec.springwebsistemaventas.Consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.cibertec.springwebsistemaventas.entity.DetalleCompra;

public class ConsultasDetalleCompra {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdsistemaventas");
        EntityManager em = emf.createEntityManager();

        DetalleCompra detalleCompra = em.find(DetalleCompra.class,"1");

        System.out.println(detalleCompra);
    }
}
