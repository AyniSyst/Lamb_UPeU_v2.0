package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "observaciones_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObservacionDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_documento_persona", nullable = false)
    private Long idDocumentoPersona;

    @Column(name = "observacion", length = 255, nullable = false)
    private String observacion;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
