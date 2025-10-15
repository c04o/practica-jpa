package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleados")
@Getter @Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre requerido")
    @Size(min = 2, max = 50)
    @Column(name = "nombre_empleado", nullable = false)
    private String nombre;

    @NotBlank(message = "Apellido requerido")
    @Size(min = 2, max = 50)
    @Column(name = "apellido_empleado", nullable = false)
    private String apellido;

    @Column(name = "email_empleado", unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Override
    public String toString() {
        return "Datos del Empleado" + '\n' +
                "id: " + id +
                "| nombre: '" + nombre + '\n' +
                "| apellido: '" + apellido + '\n' +
                "| email: '" + email + '\n' +
                "| cargo: " + (cargo != null ? cargo.getNombre() : "Sin cargo") + '\n' +
                "=================================================";
    }
}