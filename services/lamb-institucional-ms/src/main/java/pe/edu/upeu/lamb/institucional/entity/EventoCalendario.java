package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Evento concreto dentro de un calendario academico (p. ej. inicio de
 * matricula, examen final).
 */
@Entity
@Table(name = "eventos_calendario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoCalendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_calendario_academico", nullable = false)
    private Long idCalendarioAcademico;

    @Column(name = "id_tipo_evento", nullable = false)
    private Long idTipoEvento;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
}
