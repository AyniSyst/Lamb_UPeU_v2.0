package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menciones_especialidad")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MencionEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
