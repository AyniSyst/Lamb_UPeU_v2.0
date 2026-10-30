package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "arqueos_caja")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArqueoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesion_caja", nullable = false)
    private SesionCaja sesionCaja;

    @Column(name = "monto_contado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoContado;

    @Column(name = "monto_sistema", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoSistema;

    @Column(name = "diferencia", nullable = false, precision = 12, scale = 2)
    private BigDecimal diferencia;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @Column(name = "fecha_arqueo", nullable = false)
    @Builder.Default
    private LocalDateTime fechaArqueo = LocalDateTime.now();
}
