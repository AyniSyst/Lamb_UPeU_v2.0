package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.RequisitoExtracurricular;
import pe.edu.upeu.lamb.curriculo.entity.TipoActividadFormacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisitoExtracurricularRepository extends JpaRepository<RequisitoExtracurricular, Long> {

    List<RequisitoExtracurricular> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndTipo(Long idPlan, TipoActividadFormacion tipo);

    boolean existsByPlanIdAndTipoAndIdNot(Long idPlan, TipoActividadFormacion tipo, Long id);
}
