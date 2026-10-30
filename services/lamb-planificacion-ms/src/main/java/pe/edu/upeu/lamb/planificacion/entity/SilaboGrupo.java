package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 25 · Silabo de grupo (parte del silabo base de curriculo)
@Entity
@Table(name = "silabos_grupo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SilaboGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    // Silabo base del curso: se jala de lamb-curriculo-ms (CRUD 15 de curriculo)
    @Column(name = "id_silabo_base", nullable = true)
    private Long idSilaboBase;

    @Column(name = "metodologia", nullable = true, length = 1000)
    private String metodologia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoSilabo estado;

    @Column(name = "fecha_presentacion", nullable = true)
    private LocalDate fechaPresentacion;
}
