package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "insignias_estudiante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insignia", nullable = false)
    private Insignia insignia;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "fecha_otorgamiento", nullable = false)
    @Builder.Default
    private LocalDateTime fechaOtorgamiento = LocalDateTime.now();
}
