package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "resoluciones_programa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionPrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "numero_resolucion", nullable = false, length = 100)
    private String numeroResolucion;

    @Column(name = "tipo_resolucion", length = 100)
    private String tipoResolucion;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;
}
