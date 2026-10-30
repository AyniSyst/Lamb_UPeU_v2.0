package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 15 · Silabo base del curso (planificacion lo jala para el silabo de cada grupo)
@Entity
@Table(name = "silabos_base")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SilaboBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private CursoCatalogo curso;

    @Column(name = "version", nullable = false, length = 20)
    private String version;

    @Column(name = "competencias", nullable = true, length = 1000)
    private String competencias;

    @Column(name = "metodologia", nullable = true, length = 1000)
    private String metodologia;

    @Column(name = "evaluacion", nullable = true, length = 1000)
    private String evaluacion;

    @Column(name = "bibliografia", nullable = true, length = 2000)
    private String bibliografia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoSilabo estado;
}
