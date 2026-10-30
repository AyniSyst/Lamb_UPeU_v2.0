package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "acreditaciones_programa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcreditacionPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "entidad_acreditadora", length = 150)
    private String entidadAcreditadora;

    @Column(name = "fecha_acreditacion")
    private LocalDate fechaAcreditacion;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;
}
