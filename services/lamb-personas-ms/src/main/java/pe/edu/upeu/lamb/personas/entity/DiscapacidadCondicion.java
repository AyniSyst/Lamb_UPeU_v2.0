package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "discapacidades_condiciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscapacidadCondicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "observacion", length = 255)
    private String observacion;

    @Column(name = "fecha_registro")
    private Instant fechaRegistro;
}
