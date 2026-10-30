package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 29 · Perfil de ingreso (lo consulta Admision)
@Entity
@Table(name = "perfiles_ingreso")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "descripcion", nullable = false, length = 2000)
    private String descripcion;

    @Column(name = "conocimientos", nullable = true, length = 1000)
    private String conocimientos;

    @Column(name = "habilidades", nullable = true, length = 1000)
    private String habilidades;

    @Column(name = "actitudes", nullable = true, length = 1000)
    private String actitudes;
}
