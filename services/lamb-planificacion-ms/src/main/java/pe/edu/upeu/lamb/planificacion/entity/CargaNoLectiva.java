package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 22 · Carga no lectiva (investigacion, tutoria, gestion)
@Entity
@Table(name = "cargas_no_lectivas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargaNoLectiva {

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
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoCargaNoLectiva tipo;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;

    @Column(name = "horas_semanales", nullable = false)
    private Integer horasSemanales;
}
