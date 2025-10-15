package repository.dao;

import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.IEmpleado;

import java.util.List;

public class EmpleadoDao implements IEmpleado {

    private final EntityManager em;

    public EmpleadoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        try {
            em.getTransaction().begin();

            if (empleado.getId() == null) {
                em.persist(empleado); // nuevo registro
            } else {
                empleado = em.merge(empleado); // actualización
            }

            em.getTransaction().commit();
            return empleado;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Empleado> listar() {
        return em.createQuery("from Empleado", Empleado.class).getResultList();
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return em.find(Empleado.class, id);
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}