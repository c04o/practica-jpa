package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter @Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_empleado", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombres", length = 50, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 50, nullable = false)
    private String apellidos;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Override
    public String toString() {
        return "Datos del Empleado" + '\n' +
                "id: " + id + '\n' +
                "| código: " + codigo + '\n' +
                "| nombre: " + nombres + " " + apellidos + '\n' +
                "| email: " + email + '\n' +
                "| cargo: " + (cargo != null ? cargo.getNombre() : "Sin cargo") + '\n' +
                "=================================================";
    }
}