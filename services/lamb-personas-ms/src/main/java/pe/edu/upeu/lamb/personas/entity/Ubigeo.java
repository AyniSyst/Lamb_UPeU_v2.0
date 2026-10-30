package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ubigeos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais", nullable = false, length = 60)
    private String pais;

    @Column(name = "departamento", length = 60)
    private String departamento;

    @Column(name = "provincia", length = 60)
    private String provincia;

    @Column(name = "distrito", length = 60)
    private String distrito;
}
