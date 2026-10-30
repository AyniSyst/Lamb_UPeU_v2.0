package pe.edu.upeu.lamb.personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.personas.entity.ConsentimientoDatos;

public interface ConsentimientoDatosRepository extends JpaRepository<ConsentimientoDatos, Long> {
}
