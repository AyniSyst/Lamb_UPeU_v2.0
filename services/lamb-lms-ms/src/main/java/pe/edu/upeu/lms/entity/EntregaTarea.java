package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas_tarea")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregaTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea", nullable = false)
    private Tarea tarea;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "url_archivo", length = 500)
    private String urlArchivo;

    @Column(name = "fecha_entrega", nullable = false)
    @Builder.Default
    private LocalDateTime fechaEntrega = LocalDateTime.now();

    @Column(name = "nota", precision = 5, scale = 2)
    private BigDecimal nota;
}
