package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 27 · Cronograma de sesiones (programada, dictada, reprogramada)
@Entity
@Table(name = "cronogramas_sesion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CronogramaSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesion", nullable = false)
    private SesionClase sesion;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @Column(name = "fecha_ejecutada", nullable = true)
    private LocalDate fechaEjecutada;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoSesion estado;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
