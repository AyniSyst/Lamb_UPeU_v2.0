package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    // La modalidad se expone con su lista de documentos: sin esto el JSON entra en ciclo
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private ModalidadAdmision modalidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "obligatorio", nullable = false)
    private Boolean obligatorio;
}
