package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "casos_especiales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasoEspecial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postulante", nullable = false)
    private Postulante postulante;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_caso", nullable = false, length = 30)
    private TipoCaso tipoCaso;

    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "requiere_ajuste", nullable = false)
    private Boolean requiereAjuste;

    @Column(name = "ajuste_otorgado", length = 500)
    private String ajusteOtorgado;

    @Column(name = "nivel_socioeconomico", length = 20)
    private String nivelSocioeconomico;

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCaso estado;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "fecha_resolucion")
    private Instant fechaResolucion;
}
