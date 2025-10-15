package repository.dao;

import entities.Cargo;
import jakarta.persistence.EntityManager;
import repository.ICargo;

import java.util.List;

public class CargoDao implements ICargo {

    private final EntityManager em;

    public CargoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cargo guardar(Cargo cargo) {
        try {
            em.getTransaction().begin();

            if (cargo.getId() == null) {
                em.persist(cargo);
            } else {
                cargo = em.merge(cargo);
            }

            em.getTransaction().commit();
            return cargo;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Cargo> listar() {
        return em.createQuery("from Cargo", Cargo.class).getResultList();
    }

    @Override
    public Cargo buscarPorId(Long id) {
        return em.find(Cargo.class, id);
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            Cargo cargo = em.find(Cargo.class, id);
            if (cargo != null) {
                em.remove(cargo);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}