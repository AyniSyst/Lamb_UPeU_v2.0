package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "casos_seguimiento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasoSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_alerta", nullable = false)
    private Long idAlerta;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "descripcion_intervencion", nullable = false, length = 1000)
    private String descripcionIntervencion;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "resultado", length = 500)
    private String resultado;
}
