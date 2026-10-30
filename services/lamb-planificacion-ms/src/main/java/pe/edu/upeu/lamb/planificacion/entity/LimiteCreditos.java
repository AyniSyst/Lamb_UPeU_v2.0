package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 12 · Limite de creditos por ciclo, programa y periodo
@Entity
@Table(name = "limites_creditos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimiteCreditos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "ciclo", nullable = false)
    private Integer ciclo;

    @Column(name = "creditos_minimos", nullable = false)
    private Integer creditosMinimos;

    @Column(name = "creditos_maximos", nullable = false)
    private Integer creditosMaximos;
}
