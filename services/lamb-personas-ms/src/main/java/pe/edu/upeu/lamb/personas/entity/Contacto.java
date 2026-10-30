package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "contactos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_tipo_contacto", nullable = false)
    private Long idTipoContacto;

    @Column(name = "valor", length = 150, nullable = false)
    private String valor;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
