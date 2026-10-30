package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "reglas_alerta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReglaAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo_alerta", nullable = false, length = 30)
    private String tipoAlerta;

    @Column(name = "umbral", nullable = false, precision = 5, scale = 2)
    private BigDecimal umbral;

    @Column(name = "nivel_riesgo", nullable = false, length = 20)
    private String nivelRiesgo;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "descripcion", length = 500)
    private String descripcion;
}
