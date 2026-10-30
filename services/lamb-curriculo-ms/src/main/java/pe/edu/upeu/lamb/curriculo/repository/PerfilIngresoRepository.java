package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.PerfilIngreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilIngresoRepository extends JpaRepository<PerfilIngreso, Long> {

    List<PerfilIngreso> findByPlanId(Long idPlan);

    boolean existsByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdNot(Long idPlan, Long id);
}
