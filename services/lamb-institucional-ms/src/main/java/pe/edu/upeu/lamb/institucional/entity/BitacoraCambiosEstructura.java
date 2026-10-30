package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Bitacora de cambios realizados sobre la estructura institucional
 * (auditoria), item 44 (opcional) de la lista de 44 recursos.
 */
@Entity
@Table(name = "bitacoras_cambios_estructura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraCambiosEstructura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entidad_afectada", length = 100, nullable = false)
    private String entidadAfectada;

    @Column(name = "id_entidad_afectada")
    private Long idEntidadAfectada;

    @Column(name = "accion", length = 100, nullable = false)
    private String accion;

    @Column(name = "detalle", length = 255)
    private String detalle;

    @Column(name = "fecha_cambio")
    private Instant fechaCambio;
}
