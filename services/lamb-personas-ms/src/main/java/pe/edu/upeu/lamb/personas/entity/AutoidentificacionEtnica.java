package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "autoidentificaciones_etnicas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoidentificacionEtnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "etnia", nullable = false, length = 100)
    private String etnia;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
