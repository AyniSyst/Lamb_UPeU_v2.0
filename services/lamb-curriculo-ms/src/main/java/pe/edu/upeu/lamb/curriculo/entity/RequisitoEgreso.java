package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// CRUD 33 · Requisito de egreso (creditos minimos). Trayectoria registra si el estudiante lo cumple
@Entity
@Table(name = "requisitos_egreso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoEgreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "creditos_obligatorios", nullable = false)
    private Integer creditosObligatorios;

    @Column(name = "creditos_electivos", nullable = false)
    private Integer creditosElectivos;

    @Column(name = "creditos_totales", nullable = false)
    private Integer creditosTotales;

    @Column(name = "promedio_minimo", nullable = true, precision = 4, scale = 2)
    private BigDecimal promedioMinimo;
}
