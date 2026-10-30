package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

// CRUD 2 · Peso de cada componente en la nota final del grupo (la suma por grupo no pasa de 100)
@Entity
@Table(name = "configuraciones_evaluacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_componente", nullable = false)
    private ComponenteEvaluacion componente;

    @Column(name = "peso", nullable = false, precision = 5, scale = 2)
    private BigDecimal peso;
}
