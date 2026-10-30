package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_documento_identidad")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumentoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
