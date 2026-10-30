package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cumplimientos_requisito")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CumplimientoRequisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "tipo_requisito", nullable = false, length = 30)
    private String tipoRequisito;

    @Column(name = "id_requisito_externo")
    private Long idRequisitoExterno;

    @Column(name = "codigo_externo", length = 50)
    private String codigoExterno;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "cumplido", nullable = false)
    private boolean cumplido;

    @Column(name = "valor_cumplimiento", length = 200)
    private String valorCumplimiento;

    @Column(name = "creditos_reconocidos")
    private Integer creditosReconocidos;

    @Column(name = "fecha_cumplimiento")
    private LocalDate fechaCumplimiento;

    @Column(name = "evidencia_url", length = 500)
    private String evidenciaUrl;

    @Column(name = "observacion", length = 500)
    private String observacion;
}
