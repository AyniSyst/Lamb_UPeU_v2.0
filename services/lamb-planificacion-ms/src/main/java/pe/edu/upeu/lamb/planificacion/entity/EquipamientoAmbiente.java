package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 18 · Equipamiento del ambiente
@Entity
@Table(name = "equipamientos_ambiente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipamientoAmbiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ambiente", nullable = false)
    private Ambiente ambiente;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "codigo_patrimonial", nullable = true, length = 30)
    private String codigoPatrimonial;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private EstadoEquipamiento estado;
}
