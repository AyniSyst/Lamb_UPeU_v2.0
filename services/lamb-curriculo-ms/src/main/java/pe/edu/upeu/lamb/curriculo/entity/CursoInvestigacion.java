package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 25 · Curso de investigacion asociado a una linea (relacion linea x curso del plan)
@Entity
@Table(name = "cursos_investigacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_linea", nullable = false)
    private LineaInvestigacion linea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_curso", nullable = false)
    private PlanCurso planCurso;
}
