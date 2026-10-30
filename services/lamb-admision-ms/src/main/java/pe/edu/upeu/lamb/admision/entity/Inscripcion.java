package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inscripciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    // FUSION: viene de lamb-institucional-ms (CRUD programas)
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private ModalidadAdmision modalidad;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoInscripcion estado;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "fecha_aprobacion")
    private Instant fechaAprobacion;

    @Column(name = "id_aprobado_por")
    private Long idAprobadoPor;

    // Bloqueo optimista: dos ediciones simultaneas, la 2da recibe 409
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    // Detalle: cascade ALL + orphanRemoval = una sola operacion atomica
    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DocumentoPresentado> documentos = new ArrayList<>();

    public void agregarDocumento(DocumentoPresentado doc) {
        doc.setInscripcion(this);
        this.documentos.add(doc);
    }
}
