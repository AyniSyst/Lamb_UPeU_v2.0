package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Feriado o dia no laborable que afecta al calendario academico.
 */
@Entity
@Table(name = "feriados_no_laborables")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeriadoNoLaborable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
