package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "datos_socioeconomicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoSocioeconomico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nivel_socioeconomico", length = 60)
    private String nivelSocioeconomico;

    @Column(name = "ingreso_familiar", length = 60)
    private String ingresoFamiliar;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
