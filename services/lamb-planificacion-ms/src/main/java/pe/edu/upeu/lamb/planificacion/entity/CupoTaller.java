package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 10 · Cupos de un grupo de talleres por programa (la suma no supera el cupo del taller)
@Entity
@Table(name = "cupos_taller")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CupoTaller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_taller", nullable = false)
    private GrupoTaller grupoTaller;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
