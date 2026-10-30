package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "historial_documento_identidad")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDocumentoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "tipo_cambio", length = 30)
    private String tipoCambio;

    @Column(name = "numero_anterior", length = 20)
    private String numeroAnterior;

    @Column(name = "numero_nuevo", nullable = false, length = 20)
    private String numeroNuevo;

    @Column(name = "fecha_cambio", nullable = false)
    private Instant fechaCambio;
}
