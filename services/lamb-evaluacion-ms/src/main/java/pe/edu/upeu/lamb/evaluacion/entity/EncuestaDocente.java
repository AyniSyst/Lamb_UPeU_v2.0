package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 19 · Encuesta de evaluacion docente
@Entity
@Table(name = "encuestas_docente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncuestaDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    // Periodo academico: se jala de lamb-planificacion-ms (CRUD 1 de planificacion)
    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoEncuesta estado;
}
