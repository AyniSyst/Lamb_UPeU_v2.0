package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// CRUD 13 · Acta de rectificacion de la nota final de un estudiante
@Entity
@Table(name = "actas_rectificacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaRectificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acta", nullable = false)
    private Acta acta;

    // FUSION: Estudiante es el idPersona de lamb-personas-ms (ya existe). Al fusionar: validarlo contra la matricula del grupo en lamb-matricula-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nota_anterior", nullable = false, precision = 4, scale = 2)
    private BigDecimal notaAnterior;

    @Column(name = "nota_nueva", nullable = false, precision = 4, scale = 2)
    private BigDecimal notaNueva;

    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "resolucion", nullable = true, length = 50)
    private String resolucion;

    // sale del JWT
    @Column(name = "id_solicitante", nullable = false)
    private Long idSolicitante;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoSolicitud estado;

    @Column(name = "observacion_respuesta", nullable = true, length = 255)
    private String observacionRespuesta;

    @Column(name = "id_respondido_por", nullable = true)
    private Long idRespondidoPor;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_respuesta", nullable = true)
    private LocalDateTime fechaRespuesta;
}
