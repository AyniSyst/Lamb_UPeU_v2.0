package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mallas_curriculares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallaCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "programa", nullable = false, length = 100)
    private String programa;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "version", nullable = false, length = 20)
    private String version;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
