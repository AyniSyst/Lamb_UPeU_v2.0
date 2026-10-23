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

    @Column(name = "id_seccion", nullable = false, unique = true)
    private Long idSeccion;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String codigoCurso;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "docente", length = 150)
    private String docente;

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
