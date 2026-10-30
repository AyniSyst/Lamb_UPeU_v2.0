package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "resoluciones_licenciamiento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionLicenciamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_resolucion", nullable = false, length = 100)
    private String numeroResolucion;

    @Column(name = "id_institucion", nullable = false)
    private Long idInstitucion;

    @Column(name = "tipo_licenciamiento", length = 100)
    private String tipoLicenciamiento;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;

    @Column(name = "entidad_emisora", length = 150)
    private String entidadEmisora;

    @Column(name = "observaciones", length = 255)
    private String observaciones;
}
