package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prerrequisitos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prerrequisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan_curso", nullable = false)
    private PlanCurso curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan_curso_requisito", nullable = false)
    private PlanCurso requisito;
}
