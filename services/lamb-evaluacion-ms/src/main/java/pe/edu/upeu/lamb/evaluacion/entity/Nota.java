package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "notas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_seccion", nullable = false)
    private Long idSeccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evaluacion", nullable = false, length = 20)
    private TipoEvaluacion tipoEvaluacion;

    @Column(name = "calificacion", nullable = false, precision = 4, scale = 2)
    private BigDecimal calificacion;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;
}
