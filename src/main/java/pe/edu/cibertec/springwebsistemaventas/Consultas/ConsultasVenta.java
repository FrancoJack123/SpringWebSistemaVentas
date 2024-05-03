package pe.edu.cibertec.springwebsistemaventas.Consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.cibertec.springwebsistemaventas.entity.Venta;

public class ConsultasVenta {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdsistemaventas");
        EntityManager em = emf.createEntityManager();

        Venta venta = em.find(Venta.class, "1");

        System.out.println(venta);
    }
}
