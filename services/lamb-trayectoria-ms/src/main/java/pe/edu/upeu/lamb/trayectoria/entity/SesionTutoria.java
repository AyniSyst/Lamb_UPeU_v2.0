package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones_tutoria")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionTutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tutoria", nullable = false)
    private Tutoria tutoria;

    @Column(name = "fecha_sesion", nullable = false)
    private LocalDateTime fechaSesion;

    @Column(name = "tema", nullable = false, length = 200)
    private String tema;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @Column(name = "recomendaciones", length = 1000)
    private String recomendaciones;

    @Column(name = "compromiso_estudiante", length = 500)
    private String compromisoEstudiante;

    @Column(name = "derivacion_bienestar", length = 100)
    private String derivacionBienestar;
}
