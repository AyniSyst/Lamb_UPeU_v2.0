package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "cambios_nombre_apellido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioNombreApellido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "nombre_anterior", length = 150)
    private String nombreAnterior;

    @Column(name = "nombre_nuevo", nullable = false, length = 150)
    private String nombreNuevo;

    @Column(name = "sustento", nullable = false, length = 255)
    private String sustento;

    @Column(name = "fecha_cambio")
    private Instant fechaCambio;
}
