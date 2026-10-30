package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "codigos_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private CodigoInstitucionalTipo tipo;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
