package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "autoridades_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoridadInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "id_institucion", nullable = false)
    private Long idInstitucion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
}
