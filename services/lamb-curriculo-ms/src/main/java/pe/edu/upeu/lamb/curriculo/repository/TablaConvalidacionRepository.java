package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.TablaConvalidacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablaConvalidacionRepository extends JpaRepository<TablaConvalidacion, Long> {

    List<TablaConvalidacion> findByUniversidadId(Long idUniversidad);

    boolean existsByUniversidadIdAndPlanId(Long idUniversidad, Long idPlan);

    boolean existsByUniversidadIdAndPlanIdAndIdNot(Long idUniversidad, Long idPlan, Long id);
}
