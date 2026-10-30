package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 42 · Universidad de origen (la consulta Admision para traslados externos)
@Entity
@Table(name = "universidades_origen")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversidadOrigen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "pais", nullable = false, length = 50)
    private String pais;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoUniversidad tipo;
}
