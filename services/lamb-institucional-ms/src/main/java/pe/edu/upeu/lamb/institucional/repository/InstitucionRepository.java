package pe.edu.upeu.lamb.institucional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.institucional.entity.Institucion;

public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
}
