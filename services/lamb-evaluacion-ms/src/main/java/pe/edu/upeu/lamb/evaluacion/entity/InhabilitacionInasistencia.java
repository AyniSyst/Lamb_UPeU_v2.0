package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// CRUD 11 · Inhabilitacion por inasistencia (30 %): el porcentaje lo calcula el servicio con los registros
@Entity
@Table(name = "inhabilitaciones_inasistencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InhabilitacionInasistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // FUSION: Estudiante es el idPersona de lamb-personas-ms (ya existe). Al fusionar: validarlo contra la matricula del grupo en lamb-matricula-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "porcentaje_inasistencia", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeInasistencia;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
