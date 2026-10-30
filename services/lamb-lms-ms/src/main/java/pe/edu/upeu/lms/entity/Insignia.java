package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insignias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "criterio", length = 255)
    private String criterio;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
