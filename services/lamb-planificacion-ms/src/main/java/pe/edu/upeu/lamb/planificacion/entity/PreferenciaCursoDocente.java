package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 24 · Preferencia de cursos del docente
@Entity
@Table(name = "preferencias_curso_docente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferenciaCursoDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "prioridad", nullable = false)
    private Integer prioridad;
}
