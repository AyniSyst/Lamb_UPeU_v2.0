package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 47 · Evaluacion curricular periodica
@Entity
@Table(name = "evaluaciones_curriculares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_comision", nullable = true)
    private ComisionCurricular comision;

    // Periodo academico: se jala de lamb-planificacion-ms (CRUD 1 de planificacion)
    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado", nullable = false, length = 30)
    private ResultadoEvaluacionCurricular resultado;

    @Column(name = "conclusiones", nullable = true, length = 2000)
    private String conclusiones;
}
