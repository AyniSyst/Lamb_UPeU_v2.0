package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "programas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "grado_academico", length = 20)
    private GradoAcademico gradoAcademico;

    @Column(name = "duracion_semestres", nullable = false)
    private Integer duracionSemestres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad", nullable = false)
    private Facultad facultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_academico")
    private NivelAcademico nivelAcademico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grado_academico_catalogo")
    private GradoAcademicoCatalogo gradoAcademicoCatalogo;
}
