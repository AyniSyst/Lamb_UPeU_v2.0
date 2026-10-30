package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "id_pabellon")
    private Long idPabellon;

    @Column(name = "id_sede", nullable = false)
    private Long idSede;

    @Column(name = "tipo_ambiente", length = 50)
    private String tipoAmbiente;

    @Column(name = "capacidad")
    private Integer capacidad;
}
