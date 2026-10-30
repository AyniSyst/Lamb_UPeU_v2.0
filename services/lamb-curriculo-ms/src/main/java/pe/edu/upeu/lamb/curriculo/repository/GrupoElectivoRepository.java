package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.GrupoElectivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoElectivoRepository extends JpaRepository<GrupoElectivo, Long> {

    List<GrupoElectivo> findByPlanId(Long idPlan);
}
