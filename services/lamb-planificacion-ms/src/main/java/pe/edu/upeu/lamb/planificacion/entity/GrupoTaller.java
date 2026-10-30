package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// CRUD 8 · Grupo de talleres compartido entre programas (reemplaza a la seccion compartida)
@Entity
@Table(name = "grupos_taller")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoTaller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @ElementCollection
    @CollectionTable(name = "grupo_taller_programas", joinColumns = @JoinColumn(name = "id_grupo_taller"))
    @Column(name = "id_programa", nullable = false)
    @Builder.Default
    private List<Long> idProgramas = new ArrayList<>();
}
