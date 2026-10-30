package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// CRUD 14 · Acta complementaria (examen sustitutorio)
@Entity
@Table(name = "actas_complementarias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActaComplementaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acta", nullable = false)
    private Acta acta;

    // FUSION: Estudiante es el idPersona de lamb-personas-ms (ya existe). Al fusionar: validarlo contra la matricula del grupo en lamb-matricula-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nota_sustitutorio", nullable = false, precision = 4, scale = 2)
    private BigDecimal notaSustitutorio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "observacion", nullable = true, length = 255)
    private String observacion;
}
