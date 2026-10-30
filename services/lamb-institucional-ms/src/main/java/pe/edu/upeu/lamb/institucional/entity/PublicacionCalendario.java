package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Publicacion oficial de un calendario academico (version publicada,
 * opcional).
 */
@Entity
@Table(name = "publicaciones_calendario")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionCalendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_calendario_academico", nullable = false)
    private Long idCalendarioAcademico;

    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

    @Column(name = "url_documento", length = 255)
    private String urlDocumento;
}
