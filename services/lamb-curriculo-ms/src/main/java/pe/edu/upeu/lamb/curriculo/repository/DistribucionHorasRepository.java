package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.DistribucionHoras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistribucionHorasRepository extends JpaRepository<DistribucionHoras, Long> {

    List<DistribucionHoras> findByCursoId(Long idCurso);

    boolean existsByCursoId(Long idCurso);

    boolean existsByCursoIdAndIdNot(Long idCurso, Long id);
}
