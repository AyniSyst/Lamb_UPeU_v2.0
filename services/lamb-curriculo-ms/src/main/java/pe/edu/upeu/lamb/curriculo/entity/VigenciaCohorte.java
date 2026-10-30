package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 8 · Vigencia del plan por cohorte de ingreso (que cohortes siguen este plan)
@Entity
@Table(name = "vigencias_cohorte")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaCohorte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "cohorte_inicio", nullable = false, length = 20)
    private String cohorteInicio;

    @Column(name = "cohorte_fin", nullable = true, length = 20)
    private String cohorteFin;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
