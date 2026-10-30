package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// CRUD 43 · Curso externo convalidable y su curso equivalente del plan
@Entity
@Table(name = "cursos_externos_convalidables")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoExternoConvalidable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tabla", nullable = false)
    private TablaConvalidacion tabla;

    @Column(name = "codigo_externo", nullable = false, length = 20)
    private String codigoExterno;

    @Column(name = "nombre_externo", nullable = false, length = 200)
    private String nombreExterno;

    @Column(name = "creditos_externo", nullable = false)
    private Integer creditosExterno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_curso", nullable = false)
    private PlanCurso planCurso;

    @Column(name = "nota_minima", nullable = true, precision = 4, scale = 2)
    private BigDecimal notaMinima;
}
