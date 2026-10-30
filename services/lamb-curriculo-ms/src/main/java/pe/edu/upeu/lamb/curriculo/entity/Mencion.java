package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 23 · Mencion / especialidad
@Entity
@Table(name = "menciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "creditos_requeridos", nullable = false)
    private Integer creditosRequeridos;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
