package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reemplazos_version_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReemplazoVersionDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_documento_persona", nullable = false)
    private Long idDocumentoPersona;

    @Column(name = "id_documento_persona_anterior")
    private Long idDocumentoPersonaAnterior;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
