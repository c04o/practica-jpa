package repository;

import entities.Empleado;

import java.util.List;

public interface IEmpleado {
    Empleado guardar(Empleado empleado);
    List<Empleado> listar();
    void asignarCargo(Long empleadoId, Long cargoId);
}