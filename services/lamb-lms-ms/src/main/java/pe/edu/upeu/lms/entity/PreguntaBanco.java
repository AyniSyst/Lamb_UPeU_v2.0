package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "preguntas_banco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enunciado", nullable = false, length = 500)
    private String enunciado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoPregunta tipo;

    @Column(name = "puntaje_sugerido", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntajeSugerido;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
