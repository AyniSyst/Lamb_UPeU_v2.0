package pe.edu.upeu.lamb.trayectoria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_trayectoria", nullable = false)
    private Long idTrayectoria;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_tutor", nullable = false)
    private Long idTutor;

    @Column(name = "id_programa_tutoria")
    private Long idProgramaTutoria;

    @Column(name = "programa_tutoria", length = 100)
    private String programaTutoria;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "tutoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SesionTutoria> sesiones = new ArrayList<>();

    public void agregarSesion(SesionTutoria sesion) {
        sesion.setTutoria(this);
        sesiones.add(sesion);
    }
}
