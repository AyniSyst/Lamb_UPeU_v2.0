package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 18 · Distribucion de horas del curso (teoria, practica, laboratorio)
@Entity
@Table(name = "distribuciones_horas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistribucionHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private CursoCatalogo curso;

    @Column(name = "horas_teoria", nullable = false)
    private Integer horasTeoria;

    @Column(name = "horas_practica", nullable = false)
    private Integer horasPractica;

    @Column(name = "horas_laboratorio", nullable = false)
    private Integer horasLaboratorio;
}
