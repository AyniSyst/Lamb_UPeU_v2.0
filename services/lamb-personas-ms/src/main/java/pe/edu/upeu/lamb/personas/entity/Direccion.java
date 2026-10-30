package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_tipo_direccion", nullable = false)
    private Long idTipoDireccion;

    @Column(name = "id_ubigeo")
    private Long idUbigeo;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
