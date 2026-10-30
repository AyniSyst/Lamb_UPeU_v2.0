package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "convenios_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvenioInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_institucion", nullable = false)
    private Long idInstitucion;

    @Column(name = "nombre_convenio", nullable = false, length = 150)
    private String nombreConvenio;

    @Column(name = "institucion_socia", length = 150)
    private String institucionSocia;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
