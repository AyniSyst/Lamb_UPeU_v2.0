package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Parametro de configuracion a nivel institucional (clave/valor), item 42
 * (opcional) de la lista de 44 recursos.
 */
@Entity
@Table(name = "parametros_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametroInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clave", length = 100, nullable = false)
    private String clave;

    @Column(name = "valor", length = 255)
    private String valor;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
