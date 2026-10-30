package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas_foro")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_foro_hilo", nullable = false)
    private ForoHilo foroHilo;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "mensaje", nullable = false, length = 1000)
    private String mensaje;

    @Column(name = "fecha", nullable = false)
    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
