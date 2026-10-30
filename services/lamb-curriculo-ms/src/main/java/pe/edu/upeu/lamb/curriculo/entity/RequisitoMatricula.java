package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 19 · Requisito de matricula de un curso del plan (curso aprobado, creditos o ciclo minimo)
@Entity
@Table(name = "requisitos_matricula")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_curso", nullable = false)
    private PlanCurso planCurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoRequisitoMatricula tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_curso_requisito", nullable = true)
    private PlanCurso cursoRequisito;

    @Column(name = "creditos_minimos", nullable = true)
    private Integer creditosMinimos;

    @Column(name = "ciclo_minimo", nullable = true)
    private Integer cicloMinimo;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
}
