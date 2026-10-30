package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "convalidaciones_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvalidacionCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "institucion_origen", nullable = false, length = 150)
    private String institucionOrigen;

    @Column(name = "curso_origen", nullable = false, length = 150)
    private String cursoOrigen;

    @Column(name = "fecha_convalidacion", nullable = false)
    private LocalDate fechaConvalidacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoConvalidacion estado = EstadoConvalidacion.PENDIENTE;
}
