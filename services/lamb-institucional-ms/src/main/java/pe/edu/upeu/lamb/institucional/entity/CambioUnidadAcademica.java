package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cambios_unidad_academica")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioUnidadAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_facultad")
    private Long idFacultad;

    @Column(name = "id_escuela")
    private Long idEscuela;

    @Column(name = "tipo_cambio", nullable = false, length = 100)
    private String tipoCambio;

    @Column(name = "numero_resolucion", nullable = false, length = 100)
    private String numeroResolucion;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDate fechaCambio;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
