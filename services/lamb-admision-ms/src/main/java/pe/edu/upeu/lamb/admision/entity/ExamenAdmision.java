package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "examenes_admision")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamenAdmision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private Convocatoria convocatoria;

    @Column(name = "codigo", nullable = false, length = 30, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "fecha_examen", nullable = false)
    private Instant fechaExamen;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    // FUSION: lamb-planificacion-ms (ambientes)
    @Column(name = "id_aula")
    private Long idAula;

    @Column(name = "aforo_maximo", nullable = false)
    private Integer aforoMaximo;

    @Column(name = "aforo_asignado", nullable = false)
    private Integer aforoAsignado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoExamen estado;
}
