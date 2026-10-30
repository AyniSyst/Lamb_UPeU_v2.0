package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tarifarios_programa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifarioPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "programa", nullable = false, length = 100)
    private String programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto_pago", nullable = false)
    private ConceptoPago conceptoPago;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "vigente_desde", nullable = false)
    private LocalDate vigenteDesde;

    @Column(name = "vigente_hasta")
    private LocalDate vigenteHasta;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
