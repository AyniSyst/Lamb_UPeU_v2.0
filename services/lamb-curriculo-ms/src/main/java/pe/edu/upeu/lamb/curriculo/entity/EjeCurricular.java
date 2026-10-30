package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 22 · Eje / linea curricular
@Entity
@Table(name = "ejes_curriculares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EjeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
