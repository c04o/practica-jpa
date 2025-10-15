package repository.dao;

import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.IEmpleado;

import java.util.List;

public class EmpleadoDao implements IEmpleado {
    private EntityManager em;

    public EmpleadoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
            return empleado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar empleado", e);
        }
    }

    @Override
    public List<Empleado> listar() {
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar empleados", e);
        }
    }

    @Override
    public void asignarCargo(Long empleadoId, Long cargoId) {
        try {
            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class, empleadoId);
            Cargo cargo = em.find(Cargo.class, cargoId);
            if (empleado != null && cargo != null) {
                empleado.setCargo(cargo);
                em.merge(empleado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al asignar cargo", e);
        }
    }
}