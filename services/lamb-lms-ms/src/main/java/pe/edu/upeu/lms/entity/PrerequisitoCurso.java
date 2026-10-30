package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prerequisitos_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrerequisitoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso_prerequisito", nullable = false)
    private Curso cursoPrerequisito;
}
