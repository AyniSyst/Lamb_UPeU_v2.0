package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoInvestigacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoInvestigacionRepository extends JpaRepository<RequisitoInvestigacion, Long> {

    List<RequisitoInvestigacion> findByPlanId(Long idPlan);

    boolean existsByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdNot(Long idPlan, Long id);
}
