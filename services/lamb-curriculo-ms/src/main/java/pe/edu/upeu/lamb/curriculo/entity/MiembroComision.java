package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 45 · Miembro de comision curricular
@Entity
@Table(name = "miembros_comision")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiembroComision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comision", nullable = false)
    private ComisionCurricular comision;

    // FUSION: Persona (docente o estudiante) es de lamb-personas-ms (GET /api/v1/personas/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 30)
    private RolComision rol;

    @Column(name = "fecha_incorporacion", nullable = false)
    private LocalDate fechaIncorporacion;
}
