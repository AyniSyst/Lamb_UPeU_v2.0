package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modalidad_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalidadDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private ModalidadAdmision modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "obligatorio", nullable = false)
    private Boolean obligatorio;
}
