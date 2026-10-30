package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "redes_sociales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "red", length = 60, nullable = false)
    private String red;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
