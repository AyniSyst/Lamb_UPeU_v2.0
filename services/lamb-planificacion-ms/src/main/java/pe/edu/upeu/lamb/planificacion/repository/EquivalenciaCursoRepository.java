package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.EquivalenciaCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquivalenciaCursoRepository extends JpaRepository<EquivalenciaCurso, Long> {

    List<EquivalenciaCurso> findByCursoId(Long idCurso);

    boolean existsByCursoIdAndIdProgramaOrigenAndCursoEquivalenteIdAndIdProgramaEquivalente(Long idCurso, Long idProgramaOrigen, Long idCursoEquivalente, Long idProgramaEquivalente);

    boolean existsByCursoIdAndIdProgramaOrigenAndCursoEquivalenteIdAndIdProgramaEquivalenteAndIdNot(Long idCurso, Long idProgramaOrigen, Long idCursoEquivalente, Long idProgramaEquivalente, Long id);
}
