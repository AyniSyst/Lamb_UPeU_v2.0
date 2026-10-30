package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.CorreoInstitucional;

public interface CorreoInstitucionalRepository extends JpaRepository<CorreoInstitucional, Long> {
}
