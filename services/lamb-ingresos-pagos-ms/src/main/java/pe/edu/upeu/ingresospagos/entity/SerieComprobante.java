package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "series_comprobante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerieComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_comprobante", nullable = false)
    private TipoComprobante tipoComprobante;

    @Column(name = "serie", nullable = false, length = 10)
    private String serie;

    @Column(name = "correlativo_actual", nullable = false)
    @Builder.Default
    private Integer correlativoActual = 0;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
