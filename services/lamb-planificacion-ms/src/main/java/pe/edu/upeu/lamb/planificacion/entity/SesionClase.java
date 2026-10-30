package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 26 · Sesion de clase del silabo
@Entity
@Table(name = "sesiones_clase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SesionClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_silabo", nullable = false)
    private SilaboGrupo silabo;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "semana", nullable = false)
    private Integer semana;

    @Column(name = "tema", nullable = false, length = 255)
    private String tema;

    // Unidad de aprendizaje: se jala de lamb-curriculo-ms (CRUD 16 de curriculo)
    @Column(name = "id_unidad_aprendizaje", nullable = true)
    private Long idUnidadAprendizaje;

    @Column(name = "actividades", nullable = true, length = 1000)
    private String actividades;
}
