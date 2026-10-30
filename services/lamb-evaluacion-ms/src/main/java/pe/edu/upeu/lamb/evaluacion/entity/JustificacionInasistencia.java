package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// CRUD 10 · Justificacion de inasistencia (al aprobarse la falta pasa a JUSTIFICADA)
@Entity
@Table(name = "justificaciones_inasistencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JustificacionInasistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro", nullable = false)
    private RegistroAsistencia registro;

    @Column(name = "motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "documento_url", nullable = true, length = 500)
    private String documentoUrl;

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
