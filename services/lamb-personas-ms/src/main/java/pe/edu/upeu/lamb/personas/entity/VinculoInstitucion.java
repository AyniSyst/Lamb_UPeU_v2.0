package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "vinculos_institucion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VinculoInstitucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vinculo", nullable = false, length = 20)
    private VinculoTipo tipoVinculo;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
