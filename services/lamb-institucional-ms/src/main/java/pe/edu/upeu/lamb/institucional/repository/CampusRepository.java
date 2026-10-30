package pe.edu.upeu.lamb.institucional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.institucional.entity.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long> {
}
