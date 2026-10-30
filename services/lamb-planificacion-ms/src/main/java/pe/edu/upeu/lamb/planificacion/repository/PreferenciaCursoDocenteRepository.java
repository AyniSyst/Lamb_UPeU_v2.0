package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.PreferenciaCursoDocente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenciaCursoDocenteRepository extends JpaRepository<PreferenciaCursoDocente, Long> {

    List<PreferenciaCursoDocente> findByPeriodoId(Long idPeriodo);

    boolean existsByIdDocenteAndPeriodoIdAndCursoId(Long idDocente, Long idPeriodo, Long idCurso);

    boolean existsByIdDocenteAndPeriodoIdAndCursoIdAndIdNot(Long idDocente, Long idPeriodo, Long idCurso, Long id);
}
