package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 40 · Plan de adecuacion / transicion entre un plan y otro
@Entity
@Table(name = "planes_adecuacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanAdecuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_origen", nullable = false)
    private PlanEstudio planOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_destino", nullable = false)
    private PlanEstudio planDestino;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "resolucion", nullable = true, length = 50)
    private String resolucion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDate fechaFin;

    @Column(name = "descripcion", nullable = true, length = 1000)
    private String descripcion;
}
