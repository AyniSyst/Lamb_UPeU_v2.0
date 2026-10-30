package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 6 · Ciclo del plan (I a X)
@Entity
@Table(name = "ciclos_plan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CicloPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
}
