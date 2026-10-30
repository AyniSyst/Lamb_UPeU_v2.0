package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "recargos_mora")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecargoMora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_calculo", nullable = false, length = 20)
    private TipoCalculoRecargo tipoCalculo;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "dias_gracia", nullable = false)
    @Builder.Default
    private Integer diasGracia = 0;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
