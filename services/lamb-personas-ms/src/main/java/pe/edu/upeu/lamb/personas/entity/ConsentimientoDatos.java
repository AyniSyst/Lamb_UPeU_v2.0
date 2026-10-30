package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "consentimientos_datos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentimientoDatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "tipo_consentimiento", nullable = false, length = 100)
    private String tipoConsentimiento;

    @Column(name = "aceptado", nullable = false)
    private Boolean aceptado;

    @Column(name = "fecha_consentimiento")
    private Instant fechaConsentimiento;
}
