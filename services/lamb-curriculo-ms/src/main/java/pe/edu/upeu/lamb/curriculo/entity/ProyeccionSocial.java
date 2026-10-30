package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 28 · Responsabilidad social universitaria (proyeccion social)
@Entity
@Table(name = "proyecciones_sociales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyeccionSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 1000)
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDate fechaFin;

    @Column(name = "horas", nullable = false)
    private Integer horas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoProyecto estado;
}
