package pe.edu.upeu.lamb.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "datos_personales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "lugar_nacimiento", length = 150)
    private String lugarNacimiento;

    @Column(name = "id_sexo")
    private Long idSexo;

    @Column(name = "id_estado_civil")
    private Long idEstadoCivil;

    @Column(name = "id_nacionalidad")
    private Long idNacionalidad;

    @Column(name = "id_ubigeo_nacimiento")
    private Long idUbigeoNacimiento;
}
