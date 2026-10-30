package pe.edu.upeu.lamb.institucional.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Anio academico (p. ej. 2026), marco superior de los periodos academicos.
 */
@Entity
@Table(name = "anios_academicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnioAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
