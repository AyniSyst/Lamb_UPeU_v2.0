package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * CRUD 20 · Asignacion docente a grupo. Detalle de la relacion muchos a muchos Grupo x Docente.
 */
@Entity
@Table(name = "asignaciones_docente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 30)
    private RolDocente rol;

    @Column(name = "horas_semanales", nullable = false)
    private Integer horasSemanales;
}
