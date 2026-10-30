package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "convocatorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 30, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "fecha_examen")
    private LocalDate fechaExamen;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoConvocatoria estado;

    @Column(name = "vacantes_totales", nullable = false)
    private Integer vacantesTotales;

    @Column(name = "observacion", length = 500)
    private String observacion;
}
