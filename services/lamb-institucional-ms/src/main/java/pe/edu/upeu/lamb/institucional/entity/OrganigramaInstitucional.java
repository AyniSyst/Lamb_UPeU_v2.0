package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organigramas_institucionales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganigramaInstitucional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_institucion")
    private Long idInstitucion;

    @Column(name = "url_documento", length = 255)
    private String urlDocumento;

    @Column(name = "vigente")
    private Boolean vigente;
}
