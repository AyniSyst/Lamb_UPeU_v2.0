package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// CRUD 13 · Parametros de matricula por escuela y periodo
@Entity
@Table(name = "parametros_matricula")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametroMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoAcademico periodo;

    // FUSION: Programa/Escuela es de lamb-institucional-ms (GET /api/v1/programas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "fecha_limite_retiro", nullable = true)
    private LocalDate fechaLimiteRetiro;

    @Column(name = "permite_extemporanea", nullable = false)
    private Boolean permiteExtemporanea;

    @Column(name = "recargo_extemporanea", nullable = true, precision = 8, scale = 2)
    private BigDecimal recargoExtemporanea;
}
