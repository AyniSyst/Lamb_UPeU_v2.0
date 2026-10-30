package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 9 · Resolucion de aprobacion del plan
@Entity
@Table(name = "resoluciones_plan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "numero", nullable = false, length = 50)
    private String numero;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoResolucion tipo;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
}
