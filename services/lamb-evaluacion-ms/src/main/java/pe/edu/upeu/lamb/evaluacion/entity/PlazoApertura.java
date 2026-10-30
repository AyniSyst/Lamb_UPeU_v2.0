package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 17 · Plazo de apertura habilitado (grupo, docente, fecha de inicio y de cierre)
@Entity
@Table(name = "plazos_apertura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlazoApertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_solicitud", nullable = true)
    private SolicitudAperturaNotas solicitud;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "motivo", nullable = true, length = 255)
    private String motivo;
}
