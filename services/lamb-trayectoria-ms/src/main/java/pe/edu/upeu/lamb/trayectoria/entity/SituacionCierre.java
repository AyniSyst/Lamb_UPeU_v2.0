package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "situaciones_cierre")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacionCierre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "estado_academico", nullable = false, length = 20)
    private String estadoAcademico;

    @Column(name = "creditos_aprobados", nullable = false)
    private Integer creditosAprobados;

    @Column(name = "promedio_periodo", precision = 4, scale = 2)
    private BigDecimal promedioPeriodo;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "fecha_cierre", nullable = false)
    private LocalDateTime fechaCierre;
}
