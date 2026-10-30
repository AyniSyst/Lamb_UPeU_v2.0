package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rubricas_evaluacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RubricaEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "puntaje_maximo", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntajeMaximo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
