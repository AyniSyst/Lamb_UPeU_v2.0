package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CicloPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CicloPlanRepository extends JpaRepository<CicloPlan, Long> {

    List<CicloPlan> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndNumero(Long idPlan, Integer numero);

    boolean existsByPlanIdAndNumeroAndIdNot(Long idPlan, Integer numero, Long id);
}
