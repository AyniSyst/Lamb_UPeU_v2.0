package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoEgreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoEgresoRepository extends JpaRepository<RequisitoEgreso, Long> {

    List<RequisitoEgreso> findByPlanId(Long idPlan);

    boolean existsByPlanId(Long idPlan);

    boolean existsByPlanIdAndIdNot(Long idPlan, Long id);
}
