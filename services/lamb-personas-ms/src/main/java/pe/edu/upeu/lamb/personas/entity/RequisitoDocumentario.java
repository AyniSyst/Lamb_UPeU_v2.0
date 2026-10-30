package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "requisitos_documentarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoDocumentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tipo_documento", nullable = false)
    private Long idTipoDocumento;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "obligatorio")
    private Boolean obligatorio;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
