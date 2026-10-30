package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 41 · Tabla de convalidacion por traslado externo (universidad de origen x plan destino)
@Entity
@Table(name = "tablas_convalidacion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablaConvalidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad", nullable = false)
    private UniversidadOrigen universidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "resolucion", nullable = true, length = 50)
    private String resolucion;

    @Column(name = "vigente", nullable = false)
    private Boolean vigente;
}
