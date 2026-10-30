package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 26 · Actividad de formacion integral (espiritual, cultural, deportiva)
@Entity
@Table(name = "actividades_formacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadFormacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoActividadFormacion tipo;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Column(name = "horas", nullable = false)
    private Integer horas;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;
}
