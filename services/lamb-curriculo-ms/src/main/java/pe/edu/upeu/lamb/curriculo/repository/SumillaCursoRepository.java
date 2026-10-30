package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.SumillaCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SumillaCursoRepository extends JpaRepository<SumillaCurso, Long> {

    List<SumillaCurso> findByCursoId(Long idCurso);

    boolean existsByCursoId(Long idCurso);

    boolean existsByCursoIdAndIdNot(Long idCurso, Long id);
}
