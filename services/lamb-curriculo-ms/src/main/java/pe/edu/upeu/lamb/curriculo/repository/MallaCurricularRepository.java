package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.MallaCurricular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallaCurricularRepository extends JpaRepository<MallaCurricular, Long> {

    List<MallaCurricular> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndVersion(Long idPlan, Integer version);

    boolean existsByPlanIdAndVersionAndIdNot(Long idPlan, Integer version, Long id);
}
