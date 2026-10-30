package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "verificaciones_contacto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificacionContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_contacto", nullable = false)
    private Long idContacto;

    @Column(name = "verificado", nullable = false)
    private Boolean verificado;

    @Column(name = "fecha_verificacion")
    private Instant fechaVerificacion;
}
