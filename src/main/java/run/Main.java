package run;

import config.JPAUtil;
import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.dao.CargoDao;
import repository.dao.EmpleadoDao;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        // 1. Crear y guardar CARGO
        Cargo cargo = new Cargo();
        cargo.setNombre("Desarrollador");
        cargo.setDescripcion("Desarrolla software");

        CargoDao cargoDao = new CargoDao(em);
        System.out.println("✅ Cargo guardado:");
        System.out.println(cargoDao.guardar(cargo));

        // 2. Crear y guardar EMPLEADO
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setEmail("juan@example.com");

        EmpleadoDao empleadoDao = new EmpleadoDao(em);
        System.out.println("\n✅ Empleado guardado:");
        System.out.println(empleadoDao.guardar(empleado));

        // 3. ASIGNAR CARGO (usa ID=1 para ambos, se genera automáticamente)
        empleadoDao.asignarCargo(1L, 1L);
        System.out.println("\n✅ Cargo asignado a empleado");

        // 4. LISTAR CARGOS
        System.out.println("\n📋 LISTA DE CARGOS:");
        List<Cargo> cargos = cargoDao.listar();
        for (Cargo c : cargos) {
            System.out.println(c);
        }

        // 5. LISTAR EMPLEADOS
        System.out.println("\n📋 LISTA DE EMPLEADOS:");
        List<Empleado> empleados = empleadoDao.listar();
        for (Empleado e : empleados) {
            System.out.println(e);
        }

        // Cerrar
        em.close();
        JPAUtil.close();
        System.out.println("\n🎉 ¡APP COMPLETA EXITOSA!");
    }
}