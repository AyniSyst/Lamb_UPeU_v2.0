package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "firmas_validacion_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirmaValidacionDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_documento_persona", nullable = false)
    private Long idDocumentoPersona;

    @Column(name = "firmante", length = 150)
    private String firmante;

    @Column(name = "fecha_firma")
    private Instant fechaFirma;
}
