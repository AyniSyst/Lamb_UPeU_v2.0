package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 12 · Catalogo de cursos: el curso se define una sola vez aqui; el plan lo incluye como PlanCurso
@Entity
@Table(name = "cursos_catalogo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_departamento", nullable = true)
    private DepartamentoAcademico departamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_modalidad", nullable = true)
    private Modalidad modalidad;
}
