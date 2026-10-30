package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "instituciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social", nullable = false, length = 255)
    private String razonSocial;

    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @Column(name = "nombre_comercial", length = 150)
    private String nombreComercial;

    @Column(name = "id_tipo_institucion", nullable = false)
    private Long idTipoInstitucion;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "sitio_web", length = 150)
    private String sitioWeb;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;
}
