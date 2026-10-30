package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// CRUD 35 · Solicitud del estudiante para llevar el curso en un grupo habilitado por equivalencia (CRUD 34)
@Entity
@Table(name = "solicitudes_grupo_equivalente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudGrupoEquivalente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_habilitado", nullable = false)
    private GrupoHabilitadoEquivalencia grupoHabilitado;

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
