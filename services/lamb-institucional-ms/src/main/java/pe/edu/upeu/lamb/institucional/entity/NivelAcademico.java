package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Catalogo de nivel academico del programa (pregrado, posgrado, segunda
 * especialidad). No debe confundirse con el enum {@link GradoAcademico},
 * que representa el grado que otorga un Programa (bachiller, licenciatura,
 * maestria, doctorado) y se mantiene sin cambios como atributo de Programa.
 */
@Entity
@Table(name = "niveles_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NivelAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
