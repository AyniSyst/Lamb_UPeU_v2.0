package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "correcciones_identificacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorreccionIdentificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "campo_corregido", nullable = false, length = 50)
    private String campoCorregido;

    @Column(name = "valor_anterior", length = 100)
    private String valorAnterior;

    @Column(name = "valor_nuevo", nullable = false, length = 100)
    private String valorNuevo;

    @Column(name = "sustento", nullable = false, length = 255)
    private String sustento;

    @Column(name = "fecha_correccion", nullable = false)
    private Instant fechaCorreccion;
}
