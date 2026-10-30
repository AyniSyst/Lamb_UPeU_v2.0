package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "calificaciones_examen",
        uniqueConstraints = @UniqueConstraint(name = "uk_califica_examen_postulante", columnNames = {"id_examen", "id_postulante"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_examen", nullable = false)
    private ExamenAdmision examen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postulante", nullable = false)
    private Postulante postulante;

    @Column(name = "puntaje_total", nullable = false, precision = 6, scale = 2)
    private BigDecimal puntajeTotal;

    @Column(name = "puntaje_aptitud", precision = 6, scale = 2)
    private BigDecimal puntajeAptitud;

    @Column(name = "puntaje_conocimiento", precision = 6, scale = 2)
    private BigDecimal puntajeConocimiento;

    @Column(name = "respuestas_correctas")
    private Integer respuestasCorrectas;

    @Column(name = "respuestas_incorrectas")
    private Integer respuestasIncorrectas;

    @Column(name = "respuestas_blanco")
    private Integer respuestasBlanco;

    @Column(name = "orden_merito")
    private Integer ordenMerito;

    @Column(name = "id_supervisor")
    private Long idSupervisor;

    @Column(name = "id_encargado")
    private Long idEncargado;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "fecha_calificacion", nullable = false)
    private Instant fechaCalificacion;
}
