package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

/**
 * CRUD 17 · Horario de grupo. Detalle de la relacion muchos a muchos Grupo x Ambiente
 * (dia y rango de horas son los atributos de la relacion).
 */
@Entity
@Table(name = "horarios_grupo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ambiente", nullable = false)
    private Ambiente ambiente;

    // Copia del periodo de la oferta: la usa el constraint EXCLUDE que impide cruces de ambiente
    @Column(name = "id_periodo", nullable = false)
    private Long idPeriodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 30)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    // false cuando el grupo se anula: el ambiente queda libre en ese horario
    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
