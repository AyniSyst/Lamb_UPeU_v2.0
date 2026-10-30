package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "campus_institucional")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "id_institucion", nullable = false)
    private Long idInstitucion;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
