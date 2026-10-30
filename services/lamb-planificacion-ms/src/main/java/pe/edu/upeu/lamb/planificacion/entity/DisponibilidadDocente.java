package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

// CRUD 23 · Disponibilidad horaria del docente
@Entity
@Table(name = "disponibilidades_docente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 30)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;
}
