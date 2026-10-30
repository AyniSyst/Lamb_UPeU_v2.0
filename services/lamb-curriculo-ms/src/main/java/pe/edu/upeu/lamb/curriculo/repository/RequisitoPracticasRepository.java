package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoPracticas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoPracticasRepository extends JpaRepository<RequisitoPracticas, Long> {

    List<RequisitoPracticas> findByPlanId(Long idPlan);

    boolean existsByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdNot(Long idPlan, Long id);
}
