package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.ComponenteEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponenteEvaluacionRepository extends JpaRepository<ComponenteEvaluacion, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
