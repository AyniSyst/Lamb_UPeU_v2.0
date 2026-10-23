package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String codigoCurso;

    @Column(name = "nombre_curso", nullable = false, length = 150)
    private String nombreCurso;

    @Column(name = "ciclo", nullable = false)
    private Integer ciclo;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoCurso tipo;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Prerrequisito> prerrequisitos = new ArrayList<>();
}
