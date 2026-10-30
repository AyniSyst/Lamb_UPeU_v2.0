package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_cursos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    @Column(name = "id_acta", nullable = false)
    private Long idActa;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String codigoCurso;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Column(name = "nota_final", nullable = false, precision = 4, scale = 2)
    private BigDecimal notaFinal;

    @Column(name = "aprobado", nullable = false)
    private boolean aprobado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}
