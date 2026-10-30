package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 9 · Registro de asistencia
@Entity
@Table(name = "registros_asistencia")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // FUSION: Estudiante es el idPersona de lamb-personas-ms (ya existe). Al fusionar: validarlo contra la matricula del grupo en lamb-matricula-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    // Sesion de clase: se jala de lamb-planificacion-ms (CRUD 26 de planificacion)
    @Column(name = "id_sesion_clase", nullable = true)
    private Long idSesionClase;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoAsistencia estado;
}
