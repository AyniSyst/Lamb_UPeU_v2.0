package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// CRUD 28 · Solicitud de apertura de grupo
@Entity
@Table(name = "solicitudes_apertura_grupo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAperturaGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta", nullable = false)
    private OfertaAcademica oferta;

    @Column(name = "cupo_propuesto", nullable = false)
    private Integer cupoPropuesto;

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
