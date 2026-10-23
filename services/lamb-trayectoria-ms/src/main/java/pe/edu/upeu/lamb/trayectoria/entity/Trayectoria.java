package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trayectorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trayectoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false, unique = true)
    private Long idPersona;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "id_postulacion", nullable = false)
    private Long idPostulacion;

    @Column(name = "periodo_ingreso", nullable = false, length = 20)
    private String periodoIngreso;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoTrayectoria estado;

    @Column(name = "creditos_aprobados", nullable = false)
    private Integer creditosAprobados;

    @Column(name = "promedio_ponderado", precision = 4, scale = 2)
    private BigDecimal promedioPonderado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}
