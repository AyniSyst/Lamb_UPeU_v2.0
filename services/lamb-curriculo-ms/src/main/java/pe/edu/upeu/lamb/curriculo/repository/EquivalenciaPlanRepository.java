package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.EquivalenciaPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquivalenciaPlanRepository extends JpaRepository<EquivalenciaPlan, Long> {

    List<EquivalenciaPlan> findByPlanCursoOrigenId(Long idPlanCursoOrigen);

    boolean existsByPlanCursoOrigenIdAndPlanCursoDestinoId(Long idPlanCursoOrigen, Long idPlanCursoDestino);

    boolean existsByPlanCursoOrigenIdAndPlanCursoDestinoIdAndIdNot(Long idPlanCursoOrigen, Long idPlanCursoDestino, Long id);
}
