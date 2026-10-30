package pe.edu.upeu.lamb.planificacion.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 16 · Tipo de ambiente (aula, laboratorio, auditorio)
@Entity
@Table(name = "tipos_ambiente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoAmbiente {

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
