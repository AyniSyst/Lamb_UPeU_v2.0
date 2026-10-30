package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 10 · Malla curricular (documento adjunto: se guarda la referencia al archivo)
@Entity
@Table(name = "mallas_curriculares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallaCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "nombre_archivo", nullable = false, length = 200)
    private String nombreArchivo;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "fecha_carga", nullable = false)
    private LocalDate fechaCarga;
}
