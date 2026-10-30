package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Asociacion de un calendario academico con una facultad y/o escuela
 * profesional especifica (calendarios diferenciados por unidad academica).
 */
@Entity
@Table(name = "calendario_facultad_escuela")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioFacultadEscuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_calendario_academico", nullable = false)
    private Long idCalendarioAcademico;

    @Column(name = "id_facultad")
    private Long idFacultad;

    @Column(name = "id_escuela")
    private Long idEscuela;
}
