package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.PlanAdecuacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanAdecuacionRepository extends JpaRepository<PlanAdecuacion, Long> {

    List<PlanAdecuacion> findByPlanOrigenId(Long idPlanOrigen);

    boolean existsByPlanOrigenIdAndPlanDestinoId(Long idPlanOrigen, Long idPlanDestino);

    boolean existsByPlanOrigenIdAndPlanDestinoIdAndIdNot(Long idPlanOrigen, Long idPlanDestino, Long id);
}
