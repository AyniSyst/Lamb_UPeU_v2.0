package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.OfertaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaAcademicaRepository extends JpaRepository<OfertaAcademica, Long> {

    List<OfertaAcademica> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndIdProgramaAndCursoId(Long idPeriodo, Long idPrograma, Long idCurso);

    boolean existsByPeriodoIdAndIdProgramaAndCursoIdAndIdNot(Long idPeriodo, Long idPrograma, Long idCurso, Long id);
}
