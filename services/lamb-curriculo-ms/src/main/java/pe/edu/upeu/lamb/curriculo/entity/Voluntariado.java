package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 27 · Voluntariado / servicio misionero
@Entity
@Table(name = "voluntariados")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voluntariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "organizacion", nullable = false, length = 150)
    private String organizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoVoluntariado tipo;

    @Column(name = "horas_requeridas", nullable = false)
    private Integer horasRequeridas;

    @Column(name = "descripcion", nullable = true, length = 500)
    private String descripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
