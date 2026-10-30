package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "correos_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorreoInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "correo", length = 150, nullable = false)
    private String correo;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
