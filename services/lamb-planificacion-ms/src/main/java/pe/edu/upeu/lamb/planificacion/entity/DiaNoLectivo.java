package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

// CRUD 5 · Dia no lectivo / feriado
@Entity
@Table(name = "dias_no_lectivos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaNoLectivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_calendario", nullable = false)
    private CalendarioAcademico calendario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "motivo", nullable = false, length = 150)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoDiaNoLectivo tipo;
}
