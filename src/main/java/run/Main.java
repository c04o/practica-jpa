package run;

import config.JPAUtil;
import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.dao.CargoDao;
import repository.dao.EmpleadoDao;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        CargoDao cargoDao = new CargoDao(em);
        EmpleadoDao empleadoDao = new EmpleadoDao(em);
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n===== MENÚ DE GESTIÓN DE LA EMPRESA =====");
            System.out.println("1. Registrar un nuevo Cargo");
            System.out.println("2. Registrar un nuevo Empleado");
            System.out.println("3. Listar todos los Cargos");
            System.out.println("4. Listar todos los Empleados");
            System.out.println("5. Buscar Cargo por ID");
            System.out.println("6. Buscar Empleado por ID");
            System.out.println("7. Eliminar Cargo");
            System.out.println("8. Eliminar Empleado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    Cargo cargo = new Cargo();
                    System.out.print("Nombre del cargo: ");
                    cargo.setNombre(sc.nextLine());
                    System.out.print("Descripción: ");
                    cargo.setDescripcion(sc.nextLine());

                    cargoDao.guardar(cargo);
                    System.out.println("Cargo registrado exitosamente.");
                }

                case 2 -> {
                    System.out.println("== Registrar nuevo empleado ==");

                    Empleado emp = new Empleado();
                    System.out.print("Código del empleado: ");
                    emp.setCodigo(sc.nextLine());
                    System.out.print("Nombres: ");
                    emp.setNombres(sc.nextLine());
                    System.out.print("Apellidos: ");
                    emp.setApellidos(sc.nextLine());
                    System.out.print("Email: ");
                    emp.setEmail(sc.nextLine());

                    System.out.println("Seleccione el ID del cargo del empleado:");
                    List<Cargo> cargos = cargoDao.listar();
                    cargos.forEach(c -> System.out.println(c.getId() + " - " + c.getNombre()));

                    System.out.print("ID del cargo: ");
                    Long idCargo = sc.nextLong();
                    sc.nextLine();

                    Cargo cargoSeleccionado = cargoDao.buscarPorId(idCargo);
                    if (cargoSeleccionado != null) {
                        emp.setCargo(cargoSeleccionado);
                        empleadoDao.guardar(emp);
                        System.out.println("Empleado registrado exitosamente.");
                    } else {
                        System.out.println("Cargo no encontrado, no se pudo registrar el empleado.");
                    }
                }

                case 3 -> {
                    System.out.println("== LISTA DE CARGOS ==");
                    List<Cargo> cargos = cargoDao.listar();
                    cargos.forEach(System.out::println);
                }

                case 4 -> {
                    System.out.println("== LISTA DE EMPLEADOS ==");
                    List<Empleado> empleados = empleadoDao.listar();
                    empleados.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.print("Ingrese el ID del cargo a buscar: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    Cargo cargo = cargoDao.buscarPorId(id);
                    if (cargo != null) {
                        System.out.println("Cargo encontrado:\n" + cargo);
                    } else {
                        System.out.println("No se encontró el cargo con ID " + id);
                    }
                }

                case 6 -> {
                    System.out.print("Ingrese el ID del empleado a buscar: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    Empleado emp = empleadoDao.buscarPorId(id);
                    if (emp != null) {
                        System.out.println("Empleado encontrado:\n" + emp);
                    } else {
                        System.out.println("No se encontró el empleado con ID " + id);
                    }
                }

                case 7 -> {
                    System.out.print("Ingrese el ID del cargo a eliminar: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    cargoDao.eliminar(id);
                    System.out.println("Cargo eliminado correctamente.");
                }

                case 8 -> {
                    System.out.print("Ingrese el ID del empleado a eliminar: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    empleadoDao.eliminar(id);
                    System.out.println("Empleado eliminado correctamente.");
                }

                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }

        } while (opcion != 0);

        sc.close();
        em.close();
        JPAUtil.close();
    }
}