package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.PerfilEgreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilEgresoRepository extends JpaRepository<PerfilEgreso, Long> {

    List<PerfilEgreso> findByPlanId(Long idPlan);

    boolean existsByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdNot(Long idPlan, Long id);
}
