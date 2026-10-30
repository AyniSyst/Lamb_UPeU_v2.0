package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "egresos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false, unique = true)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "periodo_egreso", nullable = false, length = 20)
    private String periodoEgreso;

    @Column(name = "fecha_egreso", nullable = false)
    private LocalDate fechaEgreso;

    @Column(name = "creditos_totales", nullable = false)
    private Integer creditosTotales;

    @Column(name = "promedio_final", precision = 4, scale = 2)
    private BigDecimal promedioFinal;

    @Column(name = "todos_requisitos_cumplidos", nullable = false)
    private boolean todosRequisitosCumplidos;

    @Column(name = "numero_resolucion", length = 50)
    private String numeroResolucion;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
