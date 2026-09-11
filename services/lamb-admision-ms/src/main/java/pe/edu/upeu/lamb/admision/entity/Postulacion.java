package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "postulaciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "puntaje_examen", precision = 5, scale = 2)
    private BigDecimal puntajeExamen;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoPostulacion estado;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
