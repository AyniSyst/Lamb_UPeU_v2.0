package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_direccion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false, unique = true)
    private String nombre;
}
