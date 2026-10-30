package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Recordatorio asociado a un evento de calendario (opcional).
 */
@Entity
@Table(name = "recordatorios_calendario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordatorioCalendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_evento_calendario", nullable = false)
    private Long idEventoCalendario;

    @Column(name = "mensaje", length = 255)
    private String mensaje;

    @Column(name = "fecha_envio")
    private Instant fechaEnvio;
}
