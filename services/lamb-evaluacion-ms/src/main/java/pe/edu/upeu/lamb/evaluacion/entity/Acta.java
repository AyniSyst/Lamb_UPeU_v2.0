package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_grupo", nullable = false, unique = true)
    private Long idGrupo;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String codigoCurso;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente")
    private Long idDocente;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoActa estado;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;
}
