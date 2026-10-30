package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitacoras_auditoria_pago")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraAuditoriaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago", nullable = false)
    private Pago pago;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion", nullable = false, length = 20)
    private AccionBitacora accion;

    @Column(name = "id_usuario", nullable = false, length = 36)
    private String idUsuario;

    @Column(name = "fecha", nullable = false)
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(name = "observaciones", length = 255)
    private String observaciones;
}
