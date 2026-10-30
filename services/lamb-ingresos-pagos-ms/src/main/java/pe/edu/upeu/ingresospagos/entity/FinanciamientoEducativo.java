package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "financiamientos_educativos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanciamientoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad_financiera", nullable = false)
    private EntidadFinanciera entidadFinanciera;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "monto_financiado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoFinanciado;

    @Column(name = "numero_cuotas", nullable = false)
    private Integer numeroCuotas;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoFinanciamiento estado = EstadoFinanciamiento.ACTIVO;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
