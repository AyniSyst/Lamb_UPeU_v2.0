package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "anuncios_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "mensaje", nullable = false, length = 1000)
    private String mensaje;

    @Column(name = "fecha_publicacion", nullable = false)
    @Builder.Default
    private LocalDateTime fechaPublicacion = LocalDateTime.now();
}
