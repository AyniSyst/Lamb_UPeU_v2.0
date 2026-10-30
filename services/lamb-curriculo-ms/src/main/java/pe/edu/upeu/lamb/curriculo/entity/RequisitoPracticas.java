package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 35 · Requisito de practicas preprofesionales
@Entity
@Table(name = "requisitos_practicas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoPracticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "horas_minimas", nullable = false)
    private Integer horasMinimas;

    @Column(name = "ciclo_desde", nullable = false)
    private Integer cicloDesde;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
