package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.TipoDireccion;

public interface TipoDireccionRepository extends JpaRepository<TipoDireccion, Long> {
}
