package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamentos_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "id_facultad", nullable = false)
    private Long idFacultad;

    @Column(name = "jefe_departamento", length = 150)
    private String jefeDepartamento;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
