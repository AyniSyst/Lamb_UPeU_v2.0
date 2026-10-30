package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos_refuerzo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoRefuerzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plan_refuerzo", nullable = false)
    private PlanRefuerzo planRefuerzo;

    @Column(name = "codigo_curso", nullable = false, length = 20)
    private String codigoCurso;

    @Column(name = "nombre_curso", length = 150)
    private String nombreCurso;

    @Column(name = "numero_repitencia", nullable = false)
    private Integer numeroRepitencia;

    @Column(name = "actividad_asignada", length = 500)
    private String actividadAsignada;

    @Column(name = "completado", nullable = false)
    private boolean completado;

    @Column(name = "observacion", length = 300)
    private String observacion;
}
