package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 15 · Aula / ambiente (incluye laboratorios y locales de examen de Admision)
@Entity
@Table(name = "ambientes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ambiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pabellon", nullable = false)
    private Pabellon pabellon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ambiente", nullable = false)
    private TipoAmbiente tipoAmbiente;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "piso", nullable = false)
    private Integer piso;

    @Column(name = "aforo", nullable = false)
    private Integer aforo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
