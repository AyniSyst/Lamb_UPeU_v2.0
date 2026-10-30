package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.ModalidadTitulacion;
import pe.edu.upeu.lamb.curriculo.entity.TipoTitulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModalidadTitulacionRepository extends JpaRepository<ModalidadTitulacion, Long> {

    List<ModalidadTitulacion> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndTipo(Long idPlan, TipoTitulacion tipo);

    boolean existsByPlanIdAndTipoAndIdNot(Long idPlan, TipoTitulacion tipo, Long id);
}
