package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "relaciones_familiares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelacionFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_persona_relacionada", nullable = false)
    private Long idPersonaRelacionada;

    @Column(name = "id_tipo_parentesco", nullable = false)
    private Long idTipoParentesco;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
