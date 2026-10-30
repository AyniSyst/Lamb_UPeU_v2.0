package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 21 · Carga lectiva maxima del docente en el periodo (limite que respeta la apertura de grupos)
@Entity
@Table(name = "cargas_lectivas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargaLectiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    @Column(name = "horas_maximas", nullable = false)
    private Integer horasMaximas;
}
