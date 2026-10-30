package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_parentesco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoParentesco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60, nullable = false, unique = true)
    private String nombre;
}
