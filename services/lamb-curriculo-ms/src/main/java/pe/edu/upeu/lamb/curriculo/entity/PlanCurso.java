package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD 12 · Curso dentro del plan (PlanCurso): relacion muchos a muchos PlanEstudio x CursoCatalogo,
 * con el ciclo, el tipo y el area curricular como atributos de la relacion.
 */
@Entity
@Table(name = "plan_cursos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    // El curso se define una sola vez en el catalogo; codigo, nombre y creditos salen de ahi
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso_catalogo", nullable = false)
    private CursoCatalogo cursoCatalogo;

    @Column(name = "ciclo", nullable = false)
    private Integer ciclo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_curso", nullable = false)
    private TipoCurso tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area_curricular")
    private AreaCurricular areaCurricular;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Prerrequisito> prerrequisitos = new ArrayList<>();

    public String getCodigoCurso() {
        return cursoCatalogo.getCodigo();
    }

    public String getNombreCurso() {
        return cursoCatalogo.getNombre();
    }

    public Integer getCreditos() {
        return cursoCatalogo.getCreditos();
    }
}
