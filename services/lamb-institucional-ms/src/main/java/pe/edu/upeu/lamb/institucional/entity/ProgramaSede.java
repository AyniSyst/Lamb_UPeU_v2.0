package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "programa_sede")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaSede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "vigente")
    private Boolean vigente;
}
