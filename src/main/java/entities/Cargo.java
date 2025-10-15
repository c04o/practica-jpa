package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargos")
@Getter @Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cargo", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion_cargo", length = 255)
    private String descripcion;

    @Override
    public String toString() {
        return "Datos del Cargo" + '\n' +
                "id: " + id +
                "| nombre: '" + nombre + '\n' +
                "| descripcion: '" + descripcion + '\n' +
                "=================================================";
    }
}