package repository;

import entities.Empleado;
import java.util.List;

public interface IEmpleado {
    Empleado guardar(Empleado empleado);
    List<Empleado> listar();
    Empleado buscarPorId(Long id);
    void eliminar(Long id);
}