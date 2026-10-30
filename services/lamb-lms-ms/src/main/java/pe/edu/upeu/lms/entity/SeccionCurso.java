package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "secciones_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeccionCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo_academico", nullable = false)
    private PeriodoAcademico periodoAcademico;

    @Column(name = "nombre_seccion", nullable = false, length = 10)
    private String nombreSeccion;

    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
