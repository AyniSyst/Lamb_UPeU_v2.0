package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "documentos_persona")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_tipo_documento", nullable = false)
    private Long idTipoDocumento;

    @Column(name = "url_archivo", length = 255, nullable = false)
    private String urlArchivo;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
