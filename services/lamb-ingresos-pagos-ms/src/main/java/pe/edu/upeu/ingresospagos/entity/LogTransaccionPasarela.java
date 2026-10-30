package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs_transaccion_pasarela")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTransaccionPasarela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago", nullable = false)
    private Pago pago;

    @Column(name = "proveedor", nullable = false, length = 30)
    private String proveedor;

    @Column(name = "referencia_externa", nullable = false, length = 100)
    private String referenciaExterna;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "payload_respuesta", length = 2000)
    private String payloadRespuesta;

    @Column(name = "fecha", nullable = false)
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
