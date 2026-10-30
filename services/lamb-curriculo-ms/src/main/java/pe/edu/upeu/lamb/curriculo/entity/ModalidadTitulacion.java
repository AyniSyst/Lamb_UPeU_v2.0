package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 38 · Modalidad de titulacion (tesis, suficiencia profesional)
@Entity
@Table(name = "modalidades_titulacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalidadTitulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoTitulacion tipo;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;

    @Column(name = "vigente", nullable = false)
    private Boolean vigente;
}
