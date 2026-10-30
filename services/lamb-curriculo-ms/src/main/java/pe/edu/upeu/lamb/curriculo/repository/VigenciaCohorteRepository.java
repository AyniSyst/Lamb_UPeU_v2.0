package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.VigenciaCohorte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VigenciaCohorteRepository extends JpaRepository<VigenciaCohorte, Long> {

    List<VigenciaCohorte> findByPlanId(Long idPlan);

    boolean existsByPlanIdAndCohorteInicio(Long idPlan, String cohorteInicio);

    boolean existsByPlanIdAndCohorteInicioAndIdNot(Long idPlan, String cohorteInicio, Long id);
}
