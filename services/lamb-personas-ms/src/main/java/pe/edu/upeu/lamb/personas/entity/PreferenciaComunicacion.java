package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "preferencias_comunicacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferenciaComunicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "canal", length = 60, nullable = false)
    private String canal;

    @Column(name = "horario", length = 60)
    private String horario;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
