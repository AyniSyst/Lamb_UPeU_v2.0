package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.SolicitudModificacionCurricular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudModificacionCurricularRepository extends JpaRepository<SolicitudModificacionCurricular, Long> {

    List<SolicitudModificacionCurricular> findByPlanId(Long idPlan);
}
