package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "documentos_identidad")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_tipo_documento_identidad", nullable = false)
    private Long idTipoDocumentoIdentidad;

    @Column(name = "numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
