package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "validaciones_identidad")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidacionIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "fuente", nullable = false, length = 30)
    private String fuente;

    @Column(name = "resultado", nullable = false, length = 30)
    private String resultado;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_validacion", nullable = false)
    private Instant fechaValidacion;
}
