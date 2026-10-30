package pe.edu.upeu.lamb.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 12 · Firma / visado del acta (solo sobre un acta CERRADA)
@Entity
@Table(name = "firmas_acta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirmaActa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acta", nullable = false)
    private Acta acta;

    // FUSION: Docente es del MS de docentes/RR. HH., que aun no existe. Por ahora es el idPersona de lamb-personas-ms
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 30)
    private RolFirma rol;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
}
