package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Calendario academico asociado a un periodo academico.
 */
@Entity
@Table(name = "calendarios_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_periodo_academico", nullable = false)
    private Long idPeriodoAcademico;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
