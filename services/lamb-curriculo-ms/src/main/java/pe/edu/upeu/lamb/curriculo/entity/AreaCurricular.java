package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 20 · Area curricular (estudios generales, especificos, especialidad)
@Entity
@Table(name = "areas_curriculares")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
}
