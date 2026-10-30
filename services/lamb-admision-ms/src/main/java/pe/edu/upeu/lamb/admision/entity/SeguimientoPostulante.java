package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "seguimientos_postulante")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoPostulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postulante", nullable = false)
    private Postulante postulante;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal", nullable = false, length = 20)
    private CanalSeguimiento canal;

    @Column(name = "medio_captacion", length = 50)
    private String medioCaptacion;

    @Column(name = "asunto", nullable = false, length = 200)
    private String asunto;

    @Column(name = "detalle", length = 1000)
    private String detalle;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado", nullable = false, length = 20)
    private ResultadoSeguimiento resultado;

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "fecha_contacto", nullable = false)
    private Instant fechaContacto;

    @Column(name = "proximo_contacto")
    private Instant proximoContacto;
}
