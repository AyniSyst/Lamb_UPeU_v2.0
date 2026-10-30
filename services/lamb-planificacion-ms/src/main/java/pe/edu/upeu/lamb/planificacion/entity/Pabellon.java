package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 14 · Campus / pabellon
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

    // FUSION: Sede/Filial es del MS de organizacion, que aun no existe en el repo. Solo se guarda el id
    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "numero_pisos", nullable = false)
    private Integer numeroPisos;
}
