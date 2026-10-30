package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Grado y titulo que otorga un programa (concepto distinto del enum
 * {@link GradoAcademico} embebido en Programa).
 */
@Entity
@Table(name = "grados_titulos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradoTitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "grado", length = 100)
    private String grado;

    @Column(name = "titulo", length = 150)
    private String titulo;
}
