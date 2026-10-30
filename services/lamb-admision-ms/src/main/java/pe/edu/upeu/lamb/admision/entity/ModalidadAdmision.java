package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modalidades_admision")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalidadAdmision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 30, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "vacantes_disponibles", nullable = false)
    private Integer vacantesDisponibles;

    @Column(name = "vacantes_ocupadas", nullable = false)
    private Integer vacantesOcupadas;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    // Bloqueo optimista para evitar que dos admins toquen la modalidad a la vez
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "modalidad", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ModalidadDocumento> documentosRequeridos = new ArrayList<>();
}
