package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "colegios_procedencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColegioProcedencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nombre_colegio", nullable = false, length = 150)
    private String nombreColegio;

    @Column(name = "nivel_instruccion", length = 60)
    private String nivelInstruccion;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
