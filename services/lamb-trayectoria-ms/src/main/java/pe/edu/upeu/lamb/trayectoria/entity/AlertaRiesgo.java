package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas_riesgo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertaRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nivel_riesgo", nullable = false, length = 20)
    private String nivelRiesgo;

    @Column(name = "tipo_alerta", nullable = false, length = 30)
    private String tipoAlerta;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "valor_detectado", precision = 5, scale = 2)
    private BigDecimal valorDetectado;

    @Column(name = "umbral_regla", precision = 5, scale = 2)
    private BigDecimal umbralRegla;

    @Column(name = "numero_repitencia")
    private Integer numeroRepitencia;

    @Column(name = "codigo_curso", length = 20)
    private String codigoCurso;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_deteccion", nullable = false)
    private LocalDateTime fechaDeteccion;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
}
