package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// CRUD 5 · Criterio de rubrica
@Entity
@Table(name = "criterios_rubrica")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioRubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rubrica", nullable = false)
    private Rubrica rubrica;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "puntaje_maximo", nullable = false, precision = 4, scale = 2)
    private BigDecimal puntajeMaximo;
}
