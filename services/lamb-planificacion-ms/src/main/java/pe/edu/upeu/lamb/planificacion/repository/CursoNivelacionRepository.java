package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CursoNivelacion;
import pe.edu.upeu.lamb.planificacion.entity.TipoNivelacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoNivelacionRepository extends JpaRepository<CursoNivelacion, Long> {

    List<CursoNivelacion> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndCursoIdAndTipo(Long idPeriodo, Long idCurso, TipoNivelacion tipo);

    boolean existsByPeriodoIdAndCursoIdAndTipoAndIdNot(Long idPeriodo, Long idCurso, TipoNivelacion tipo, Long id);
}
