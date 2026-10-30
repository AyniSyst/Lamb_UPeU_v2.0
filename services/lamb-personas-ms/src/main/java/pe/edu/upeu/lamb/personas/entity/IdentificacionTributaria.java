package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "identificaciones_tributarias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificacionTributaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
