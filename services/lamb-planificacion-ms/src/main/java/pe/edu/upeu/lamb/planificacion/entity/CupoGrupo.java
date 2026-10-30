package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 9 · Cupos por grupo segun tipo de estudiante (la suma no supera el cupo del grupo)
@Entity
@Table(name = "cupos_grupo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CupoGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoCupo tipo;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
