package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "contactos_emergencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactoEmergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nombre_contacto", length = 150, nullable = false)
    private String nombreContacto;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "parentesco", length = 60)
    private String parentesco;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
