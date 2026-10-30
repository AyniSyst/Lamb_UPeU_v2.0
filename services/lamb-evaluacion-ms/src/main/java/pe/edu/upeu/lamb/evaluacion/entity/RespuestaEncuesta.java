package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 21 · Respuesta de la evaluacion docente (anonima: no guarda al estudiante)
@Entity
@Table(name = "respuestas_encuesta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    private PreguntaEncuesta pregunta;

    // Grupo: se jala de lamb-planificacion-ms (CRUD 7 de planificacion, GET /api/v1/grupos/{id})
    @Column(name = "id_grupo", nullable = false)
    private Long idGrupo;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @Column(name = "valor_escala", nullable = true)
    private Integer valorEscala;

    @Column(name = "valor_texto", nullable = true, length = 1000)
    private String valorTexto;
}
