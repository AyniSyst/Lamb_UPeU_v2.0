package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "fusiones_personas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FusionPersonas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona_origen", nullable = false)
    private Long idPersonaOrigen;

    @Column(name = "id_persona_destino", nullable = false)
    private Long idPersonaDestino;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "fecha_fusion", nullable = false)
    private Instant fechaFusion;
}
