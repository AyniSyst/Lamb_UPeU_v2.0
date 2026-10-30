package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "vigencias_vencimiento_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VigenciaVencimientoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_documento_persona", nullable = false)
    private Long idDocumentoPersona;

    @Column(name = "fecha_vigencia_desde")
    private LocalDate fechaVigenciaDesde;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
