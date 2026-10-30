package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "adjudicaciones_vacante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdjudicacionVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postulante", nullable = false)
    private Postulante postulante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private ModalidadAdmision modalidad;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "puntaje_final", nullable = false, precision = 6, scale = 2)
    private BigDecimal puntajeFinal;

    @Column(name = "orden_merito")
    private Integer ordenMerito;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoAdjudicacion estado;

    @Column(name = "numero_resolucion", length = 50)
    private String numeroResolucion;

    @Column(name = "fecha_adjudicacion", nullable = false)
    private Instant fechaAdjudicacion;

    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
