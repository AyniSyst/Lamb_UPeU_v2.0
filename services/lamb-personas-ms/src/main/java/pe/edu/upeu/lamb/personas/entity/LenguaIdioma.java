package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "lenguas_idiomas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LenguaIdioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "idioma", nullable = false, length = 60)
    private String idioma;

    @Column(name = "nivel", length = 30)
    private String nivel;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
