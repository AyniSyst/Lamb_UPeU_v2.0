package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sedes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "id_campus", nullable = false)
    private Long idCampus;

    @Column(name = "id_tipo_sede", nullable = false)
    private Long idTipoSede;

    @Column(name = "direccion", length = 255)
    private String direccion;
}
