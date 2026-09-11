package pe.edu.upeu.lamb.personas.repository;

import pe.edu.upeu.lamb.personas.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
