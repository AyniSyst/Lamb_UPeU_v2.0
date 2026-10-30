package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagadores_terceros")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagadorTercero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_estudiante", nullable = false, length = 36)
    private String idEstudiante;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "tipo_documento", nullable = false, length = 20)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 20)
    private String numeroDocumento;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
