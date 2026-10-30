package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// CRUD 29 · Solicitud de cierre o fusion de grupos
@Entity
@Table(name = "solicitudes_cierre_fusion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCierreFusionGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoCierreFusion tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_grupo_destino", nullable = true)
    private Grupo grupoDestino;

    @Column(name = "justificacion", nullable = false, length = 500)
    private String justificacion;

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
