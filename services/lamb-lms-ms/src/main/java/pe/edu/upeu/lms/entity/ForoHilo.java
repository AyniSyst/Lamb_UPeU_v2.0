package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "foros_hilo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForoHilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "id_estudiante_creador", nullable = false, length = 36)
    private String idEstudianteCreador;

    @Column(name = "fecha_creacion", nullable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
