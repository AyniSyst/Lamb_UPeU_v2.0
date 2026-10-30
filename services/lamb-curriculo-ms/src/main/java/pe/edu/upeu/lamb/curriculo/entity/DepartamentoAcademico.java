package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 11 · Departamento academico responsable (jala el id del plan de estudios, CRUD 5)
@Entity
@Table(name = "departamentos_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    // FUSION: Facultad es de lamb-institucional-ms (GET /api/v1/facultades/{id}, ya existe). Al fusionar: validar el id por Feign
    @Column(name = "id_facultad", nullable = true)
    private Long idFacultad;
}
