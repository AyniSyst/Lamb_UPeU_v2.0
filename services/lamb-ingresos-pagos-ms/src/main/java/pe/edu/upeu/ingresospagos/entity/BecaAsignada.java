package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "becas_asignadas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BecaAsignada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_beca", nullable = false)
    private Beca beca;

    @Column(name = "fecha_asignacion", nullable = false)
    @Builder.Default
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
