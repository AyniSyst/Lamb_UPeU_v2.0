package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "informes_trayectoria")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformeTrayectoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "id_apoderado")
    private Long idApoderado;

    @Column(name = "resumen", nullable = false, length = 1000)
    private String resumen;

    @Column(name = "url_documento", length = 500)
    private String urlDocumento;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion;
}
