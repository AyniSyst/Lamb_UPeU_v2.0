package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.CalificacionCriterio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionCriterioRepository extends JpaRepository<CalificacionCriterio, Long> {

    List<CalificacionCriterio> findByCriterioId(Long idCriterio);

    boolean existsByCriterioIdAndIdGrupoAndIdPersona(Long idCriterio, Long idGrupo, Long idPersona);

    boolean existsByCriterioIdAndIdGrupoAndIdPersonaAndIdNot(Long idCriterio, Long idGrupo, Long idPersona, Long id);
}
