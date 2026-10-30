package pe.edu.upeu.lamb.curriculo.entity;

import jakarta.persistence.*;
import lombok.*;

// CRUD 34 · Requisito de idioma extranjero o nativo
@Entity
@Table(name = "requisitos_idioma")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoIdioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanEstudio plan;

    @Column(name = "idioma", nullable = false, length = 50)
    private String idioma;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 30)
    private TipoIdioma tipo;

    @Column(name = "nivel", nullable = false, length = 30)
    private String nivel;

    @Column(name = "modo_acreditacion", nullable = true, length = 200)
    private String modoAcreditacion;
}
