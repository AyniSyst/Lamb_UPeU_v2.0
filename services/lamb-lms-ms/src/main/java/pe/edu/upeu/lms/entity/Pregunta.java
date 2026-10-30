package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "preguntas_evaluacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    @Column(name = "enunciado", nullable = false, length = 500)
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoPregunta tipo;

    @Column(name = "puntaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntaje;
}
