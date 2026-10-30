package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recursos_complementarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecursoComplementario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoRecursoComplementario tipo;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
