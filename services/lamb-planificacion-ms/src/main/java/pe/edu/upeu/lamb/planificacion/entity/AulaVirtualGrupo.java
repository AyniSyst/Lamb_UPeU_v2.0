package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 19 · Aula virtual del grupo (enlace)
@Entity
@Table(name = "aulas_virtuales_grupo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaVirtualGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", nullable = false, length = 30)
    private PlataformaVirtual plataforma;

    @Column(name = "enlace", nullable = false, length = 500)
    private String enlace;

    @Column(name = "codigo_acceso", nullable = true, length = 50)
    private String codigoAcceso;
}
