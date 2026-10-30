package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 7 · Creditos minimos y maximos por ciclo
@Entity
@Table(name = "creditos_ciclo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditosCiclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciclo_plan", nullable = false)
    private CicloPlan cicloPlan;

    @Column(name = "creditos_minimos", nullable = false)
    private Integer creditosMinimos;

    @Column(name = "creditos_maximos", nullable = false)
    private Integer creditosMaximos;
}
