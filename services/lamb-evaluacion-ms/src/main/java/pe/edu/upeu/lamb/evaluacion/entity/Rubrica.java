package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 4 · Rubrica de un componente de evaluacion
@Entity
@Table(name = "rubricas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_componente", nullable = false)
    private ComponenteEvaluacion componente;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
