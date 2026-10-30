package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reconocimientos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reconocimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "tipo_reconocimiento", nullable = false, length = 30)
    private String tipoReconocimiento;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "periodo", length = 20)
    private String periodo;

    @Column(name = "id_competencia")
    private Long idCompetencia;

    @Column(name = "autoevaluacion", length = 1000)
    private String autoevaluacion;

    @Column(name = "evidencia_url", length = 500)
    private String evidenciaUrl;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}
