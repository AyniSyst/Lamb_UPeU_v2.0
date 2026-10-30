package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 16 · Unidades de aprendizaje del silabo base
@Entity
@Table(name = "unidades_aprendizaje")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadAprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_silabo", nullable = false)
    private SilaboBase silabo;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "semana_inicio", nullable = false)
    private Integer semanaInicio;

    @Column(name = "semana_fin", nullable = false)
    private Integer semanaFin;

    @Column(name = "contenidos", nullable = true, length = 2000)
    private String contenidos;
}
