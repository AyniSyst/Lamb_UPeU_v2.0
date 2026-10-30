package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 34 · Grupo de otra carrera habilitado por equivalencia y con cuantos cupos
@Entity
@Table(name = "grupos_habilitados_equivalencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoHabilitadoEquivalencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equivalencia", nullable = false)
    private EquivalenciaCurso equivalencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @Column(name = "cupos_habilitados", nullable = false)
    private Integer cuposHabilitados;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;
}
