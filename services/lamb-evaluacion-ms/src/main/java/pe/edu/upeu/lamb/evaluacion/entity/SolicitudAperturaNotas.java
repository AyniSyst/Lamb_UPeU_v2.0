package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

// CRUD 15 · Solicitud de apertura del sistema para notas rezagadas (se responde con el CRUD 16)
@Entity
@Table(name = "solicitudes_apertura_notas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAperturaNotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "fecha_inicio_propuesta", nullable = false)
    private LocalDate fechaInicioPropuesta;

    @Column(name = "fecha_fin_propuesta", nullable = false)
    private LocalDate fechaFinPropuesta;

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
