package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_documento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;
}
