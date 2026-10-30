package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidades_administrativas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadAdministrativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_facultad")
    private Long idFacultad;

    @Column(name = "responsable", length = 150)
    private String responsable;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
