package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planes_refuerzo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRefuerzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "objetivo", nullable = false, length = 500)
    private String objetivo;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @OneToMany(mappedBy = "planRefuerzo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CursoRefuerzo> cursos = new ArrayList<>();

    public void agregarCurso(CursoRefuerzo curso) {
        curso.setPlanRefuerzo(this);
        cursos.add(curso);
    }
}
