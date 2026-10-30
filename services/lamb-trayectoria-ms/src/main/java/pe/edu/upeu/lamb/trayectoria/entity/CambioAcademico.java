package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cambios_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cambio", nullable = false, length = 30)
    private TipoCambioAcademico tipoCambio;

    @Column(name = "id_plan_origen")
    private Long idPlanOrigen;

    @Column(name = "id_plan_destino")
    private Long idPlanDestino;

    @Column(name = "id_escuela_origen")
    private Long idEscuelaOrigen;

    @Column(name = "id_escuela_destino")
    private Long idEscuelaDestino;

    @Column(name = "id_sede_origen")
    private Long idSedeOrigen;

    @Column(name = "id_sede_destino")
    private Long idSedeDestino;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior", nullable = false, length = 20)
    private EstadoTrayectoria estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo", nullable = false, length = 20)
    private EstadoTrayectoria estadoNuevo;

    @Column(name = "motivo", length = 500)
    private String motivo;

    @Column(name = "numero_resolucion", length = 50)
    private String numeroResolucion;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;
}
