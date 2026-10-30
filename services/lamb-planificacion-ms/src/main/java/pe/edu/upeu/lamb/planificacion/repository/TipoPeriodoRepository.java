package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.TipoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPeriodoRepository extends JpaRepository<TipoPeriodo, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
