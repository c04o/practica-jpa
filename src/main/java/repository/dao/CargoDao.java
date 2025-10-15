package repository.dao;

import entities.Cargo;
import jakarta.persistence.EntityManager;
import repository.ICargo;

import java.util.List;

public class CargoDao implements ICargo {
    private EntityManager em;

    public CargoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cargo guardar(Cargo cargo) {
        try {
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
            return cargo;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar cargo", e);
        }
    }

    @Override
    public List<Cargo> listar() {
        try {
            return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar cargos", e);
        }
    }
}