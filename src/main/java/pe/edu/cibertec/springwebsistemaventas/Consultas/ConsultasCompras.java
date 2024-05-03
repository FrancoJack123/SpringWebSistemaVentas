package pe.edu.cibertec.springwebsistemaventas.Consultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.cibertec.springwebsistemaventas.entity.Compra;

public class ConsultasCompras {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdsistemaventas");
        EntityManager em = emf.createEntityManager();

        Compra compra = em.find(Compra.class, "1");

        System.out.println(compra);
    }
}
