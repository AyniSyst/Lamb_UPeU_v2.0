package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sesiones_clase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seccion_curso", nullable = false)
    private SeccionCurso seccionCurso;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "enlace_virtual", length = 255)
    private String enlaceVirtual;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
