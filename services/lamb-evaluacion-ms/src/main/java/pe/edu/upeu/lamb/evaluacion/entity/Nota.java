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

    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // CRUD 1: el componente reemplaza al antiguo tipo fijo (PRACTICA, PARCIAL, FINAL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_componente", nullable = false)
    private ComponenteEvaluacion componente;

    @Column(name = "calificacion", nullable = false, precision = 4, scale = 2)
    private BigDecimal calificacion;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;
}
