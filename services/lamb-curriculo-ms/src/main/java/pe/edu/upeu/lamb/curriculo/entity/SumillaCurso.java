package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 14 · Sumilla del curso
@Entity
@Table(name = "sumillas_curso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SumillaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private CursoCatalogo curso;

    @Column(name = "naturaleza", nullable = false, length = 100)
    private String naturaleza;

    @Column(name = "proposito", nullable = false, length = 1000)
    private String proposito;

    @Column(name = "contenido", nullable = false, length = 2000)
    private String contenido;
}
