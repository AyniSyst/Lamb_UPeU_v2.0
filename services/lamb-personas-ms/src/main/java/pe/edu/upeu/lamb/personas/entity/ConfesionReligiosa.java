package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "confesiones_religiosas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfesionReligiosa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "religion", length = 100)
    private String religion;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
