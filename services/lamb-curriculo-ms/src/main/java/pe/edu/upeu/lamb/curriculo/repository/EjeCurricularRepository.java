package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.EjeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjeCurricularRepository extends JpaRepository<EjeCurricular, Long> {

    List<EjeCurricular> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndNombre(Long idPlan, String nombre);

    boolean existsByPlanIdAndNombreAndIdNot(Long idPlan, String nombre, Long id);
}
