package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Asociacion de un periodo academico con un programa y una sede especifica
 * (vigencia del periodo para dicho programa/sede).
 */
@Entity
@Table(name = "periodo_programa_sede")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoProgramaSede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_periodo_academico", nullable = false)
    private Long idPeriodoAcademico;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "vigente")
    private Boolean vigente;
}
