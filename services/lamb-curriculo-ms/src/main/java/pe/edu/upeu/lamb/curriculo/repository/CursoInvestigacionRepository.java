package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CursoInvestigacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoInvestigacionRepository extends JpaRepository<CursoInvestigacion, Long> {

    List<CursoInvestigacion> findByLineaId(Long idLinea);

    boolean existsByLineaIdAndPlanCursoId(Long idLinea, Long idPlanCurso);

    boolean existsByLineaIdAndPlanCursoIdAndIdNot(Long idLinea, Long idPlanCurso, Long id);
}
