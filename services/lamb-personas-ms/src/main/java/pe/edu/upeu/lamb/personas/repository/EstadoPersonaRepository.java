package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.EstadoPersona;

public interface EstadoPersonaRepository extends JpaRepository<EstadoPersona, Long> {
}
