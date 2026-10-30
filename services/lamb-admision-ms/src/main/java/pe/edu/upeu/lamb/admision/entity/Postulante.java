package pe.edu.upeu.lamb.admision.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "postulantes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private Convocatoria convocatoria;

    // FUSION: lamb-institucional-ms
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modalidad", nullable = false)
    private ModalidadAdmision modalidad;

    @Column(name = "codigo_postulante", nullable = false, length = 30, unique = true)
    private String codigoPostulante;

    @Column(name = "colegio_procedencia", length = 200)
    private String colegioProcedencia;

    @Column(name = "anio_egreso_colegio")
    private Integer anioEgresoColegio;

    @Column(name = "promedio_colegio", precision = 4, scale = 2)
    private BigDecimal promedioColegio;

    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;

    @Column(name = "correo_contacto", length = 100)
    private String correoContacto;

    @Column(name = "nombre_apoderado", length = 150)
    private String nombreApoderado;

    @Column(name = "telefono_apoderado", length = 20)
    private String telefonoApoderado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoPostulante estado;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}
