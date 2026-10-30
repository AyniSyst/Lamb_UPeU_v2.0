package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pabellones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pabellon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "numero_pisos")
    private Integer numeroPisos;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
