package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
