package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// CRUD 8 · Calificacion por criterio de rubrica (jala el id del criterio, CRUD 5)
@Entity
@Table(name = "calificaciones_criterio")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionCriterio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_criterio", nullable = false)
    private CriterioRubrica criterio;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // FUSION: Estudiante es el idPersona de lamb-personas-ms (ya existe). Al fusionar: validarlo contra la matricula del grupo en lamb-matricula-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "puntaje", nullable = false, precision = 4, scale = 2)
    private BigDecimal puntaje;
}
