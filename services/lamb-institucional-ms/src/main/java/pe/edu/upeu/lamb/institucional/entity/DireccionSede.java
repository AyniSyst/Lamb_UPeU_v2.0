package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones_sede")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionSede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "id_ubigeo", nullable = false)
    private Long idUbigeo;

    @Column(name = "direccion_detalle", nullable = false, length = 255)
    private String direccionDetalle;

    @Column(name = "referencia", length = 255)
    private String referencia;
}
