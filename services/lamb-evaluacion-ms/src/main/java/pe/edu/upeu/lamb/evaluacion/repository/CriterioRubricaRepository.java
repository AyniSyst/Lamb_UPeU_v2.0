package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.CriterioRubrica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriterioRubricaRepository extends JpaRepository<CriterioRubrica, Long> {

    List<CriterioRubrica> findByRubricaId(Long idRubrica);

    boolean existsByRubricaIdAndOrden(Long idRubrica, Integer orden);

    boolean existsByRubricaIdAndOrdenAndIdNot(Long idRubrica, Integer orden, Long id);
}
