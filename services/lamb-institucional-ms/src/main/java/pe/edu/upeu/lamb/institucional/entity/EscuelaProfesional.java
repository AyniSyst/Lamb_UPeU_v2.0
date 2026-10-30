package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "escuelas_profesionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscuelaProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_facultad", nullable = false)
    private Long idFacultad;

    @Column(name = "director", length = 150)
    private String director;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
