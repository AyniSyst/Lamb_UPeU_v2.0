package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 2 · Denominacion del programa
@Entity
@Table(name = "denominaciones_programa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenominacionPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "denominacion", nullable = false, length = 200)
    private String denominacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private Modalidad modalidad;

    @Column(name = "duracion_semestres", nullable = false)
    private Integer duracionSemestres;

    @Column(name = "resolucion", nullable = true, length = 50)
    private String resolucion;
}
