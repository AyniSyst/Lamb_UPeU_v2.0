package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

// CRUD 31 · Solicitud de cambio de horario (al aprobarse mueve el horario, con la misma validacion de cruces)
@Entity
@Table(name = "solicitudes_cambio_horario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCambioHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horario", nullable = false)
    private HorarioGrupo horario;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 30)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

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
