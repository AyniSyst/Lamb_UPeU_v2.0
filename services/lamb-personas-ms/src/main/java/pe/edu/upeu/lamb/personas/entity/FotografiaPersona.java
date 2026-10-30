package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "fotografias_persona")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FotografiaPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "url_archivo", nullable = false, length = 255)
    private String urlArchivo;

    @Column(name = "fecha_carga", nullable = false)
    private Instant fechaCarga;
}
