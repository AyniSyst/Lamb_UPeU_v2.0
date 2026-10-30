package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "docentes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
