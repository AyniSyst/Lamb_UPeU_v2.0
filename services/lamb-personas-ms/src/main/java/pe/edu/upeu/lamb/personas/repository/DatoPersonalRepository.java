package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.DatoPersonal;

public interface DatoPersonalRepository extends JpaRepository<DatoPersonal, Long> {
}
