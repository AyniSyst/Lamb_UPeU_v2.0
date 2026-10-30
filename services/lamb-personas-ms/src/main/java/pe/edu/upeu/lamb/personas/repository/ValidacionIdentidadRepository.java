package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.ValidacionIdentidad;

public interface ValidacionIdentidadRepository extends JpaRepository<ValidacionIdentidad, Long> {
}
