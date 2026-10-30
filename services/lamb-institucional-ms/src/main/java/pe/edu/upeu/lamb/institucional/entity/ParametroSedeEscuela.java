package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Parametro de configuracion especifico de una sede/escuela, item 43
 * (opcional) de la lista de 44 recursos.
 */
@Entity
@Table(name = "parametros_sede_escuela")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParametroSedeEscuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clave", length = 100, nullable = false)
    private String clave;

    @Column(name = "valor", length = 255)
    private String valor;

    @Column(name = "id_sede")
    private Long idSede;

    @Column(name = "id_escuela")
    private Long idEscuela;
}
