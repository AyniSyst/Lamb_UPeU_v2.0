package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Matricula extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_estudiante", length = 36)
    private String idEstudiante;

    @Column(name = "periodo_academico", length = 20)
    private String periodoAcademico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private EstadoMatricula estado = EstadoMatricula.PENDIENTE_VALIDACION;

    @Column(name = "metodo_pago", nullable = false, length = 20)
    private String metodoPago;

    private BigDecimal total;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MatriculaDetalle> detalles = new ArrayList<>();
}
