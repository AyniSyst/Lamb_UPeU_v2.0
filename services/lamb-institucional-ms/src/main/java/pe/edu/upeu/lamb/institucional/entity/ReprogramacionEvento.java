package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Reprogramacion de un evento de calendario mediante resolucion.
 */
@Entity
@Table(name = "reprogramaciones_evento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReprogramacionEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_evento_calendario", nullable = false)
    private Long idEventoCalendario;

    @Column(name = "numero_resolucion", length = 100)
    private String numeroResolucion;

    @Column(name = "fecha_inicio_nueva")
    private LocalDate fechaInicioNueva;

    @Column(name = "fecha_fin_nueva")
    private LocalDate fechaFinNueva;

    @Column(name = "motivo", length = 255)
    private String motivo;
}
