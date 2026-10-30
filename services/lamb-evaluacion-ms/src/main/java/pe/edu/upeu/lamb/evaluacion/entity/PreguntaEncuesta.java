package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 20 · Pregunta de la evaluacion docente
@Entity
@Table(name = "preguntas_encuesta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encuesta", nullable = false)
    private EncuestaDocente encuesta;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "enunciado", nullable = false, length = 500)
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoPregunta tipo;
}
