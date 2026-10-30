package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.EstadoCivil;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {
}
