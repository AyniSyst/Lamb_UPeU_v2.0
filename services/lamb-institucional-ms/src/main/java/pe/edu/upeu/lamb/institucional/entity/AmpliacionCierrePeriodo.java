package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Resolucion de ampliacion o cierre anticipado de un periodo academico.
 */
@Entity
@Table(name = "ampliaciones_cierre_periodo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmpliacionCierrePeriodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_periodo_academico", nullable = false)
    private Long idPeriodoAcademico;

    @Column(name = "tipo_accion", nullable = false, length = 100)
    private String tipoAccion;

    @Column(name = "numero_resolucion", length = 100)
    private String numeroResolucion;

    @Column(name = "fecha_accion")
    private LocalDate fechaAccion;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
