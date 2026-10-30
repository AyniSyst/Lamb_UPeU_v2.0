package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 39 · Equivalencia entre cursos de dos planes
@Entity
@Table(name = "equivalencias_plan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquivalenciaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_curso_origen", nullable = false)
    private PlanCurso planCursoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_curso_destino", nullable = false)
    private PlanCurso planCursoDestino;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoEquivalencia tipo;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
