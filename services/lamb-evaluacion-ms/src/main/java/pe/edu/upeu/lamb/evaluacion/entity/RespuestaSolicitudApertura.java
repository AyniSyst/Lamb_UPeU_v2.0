package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// CRUD 16 · Respuesta / aprobacion de la solicitud de apertura (quien la aprueba y cuando)
@Entity
@Table(name = "respuestas_solicitud_apertura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaSolicitudApertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudAperturaNotas solicitud;

    @Column(name = "aprobada", nullable = false)
    private Boolean aprobada;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;

    // Quien responde: sale del JWT
    @Column(name = "id_responsable", nullable = false)
    private Long idResponsable;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
}
