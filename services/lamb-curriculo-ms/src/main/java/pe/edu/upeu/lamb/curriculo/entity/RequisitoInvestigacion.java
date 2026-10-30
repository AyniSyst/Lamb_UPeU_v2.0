package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 36 · Requisito de trabajo de investigacion (bachiller)
@Entity
@Table(name = "requisitos_investigacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "tipo_trabajo", nullable = false, length = 100)
    private String tipoTrabajo;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
