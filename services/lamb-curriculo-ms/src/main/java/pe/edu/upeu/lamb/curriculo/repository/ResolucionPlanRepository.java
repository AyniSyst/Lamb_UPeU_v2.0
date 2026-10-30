package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.ResolucionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResolucionPlanRepository extends JpaRepository<ResolucionPlan, Long> {

    List<ResolucionPlan> findByPlanId(Long idPlan);

    boolean existsByNumero(String numero);

    boolean existsByNumeroAndIdNot(String numero, Long id);
}
