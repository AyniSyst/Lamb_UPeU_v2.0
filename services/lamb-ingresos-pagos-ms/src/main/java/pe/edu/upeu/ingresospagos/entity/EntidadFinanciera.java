package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entidades_financieras")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntidadFinanciera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
