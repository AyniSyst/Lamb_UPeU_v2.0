package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Estado de un periodo academico (p. ej. planificado, en curso, cerrado).
 */
@Entity
@Table(name = "estados_periodo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoPeriodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_periodo_academico", nullable = false)
    private Long idPeriodoAcademico;

    @Column(name = "estado", nullable = false, length = 100)
    private String estado;

    @Column(name = "fecha_cambio")
    private LocalDate fechaCambio;
}
