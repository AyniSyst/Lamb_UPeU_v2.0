package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.EvaluacionCurricular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionCurricularRepository extends JpaRepository<EvaluacionCurricular, Long> {

    List<EvaluacionCurricular> findByPlanId(Long idPlan);
}
