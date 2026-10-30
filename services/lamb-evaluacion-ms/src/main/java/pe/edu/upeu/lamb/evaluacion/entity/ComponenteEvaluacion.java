package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 1 · Componente de evaluacion (evaluacion de sesiones, evaluacion de producto, examen)
@Entity
@Table(name = "componentes_evaluacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponenteEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoComponente tipo;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
}
