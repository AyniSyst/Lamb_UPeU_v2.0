package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 33 · Equivalencia o variacion de curso entre ciclos o carreras (los cursos se jalan de curriculo)
@Entity
@Table(name = "equivalencias_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquivalenciaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa_origen", nullable = false)
    private Long idProgramaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso_equivalente", nullable = false)
    private Curso cursoEquivalente;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa_equivalente", nullable = false)
    private Long idProgramaEquivalente;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
