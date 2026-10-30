package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "bajas_anonimizacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BajaAnonimizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoBaja tipo;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "fecha_aplicacion", nullable = false)
    private Instant fechaAplicacion;
}
