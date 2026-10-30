package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.PreguntaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaEncuestaRepository extends JpaRepository<PreguntaEncuesta, Long> {

    List<PreguntaEncuesta> findByEncuestaId(Long idEncuesta);

    boolean existsByEncuestaIdAndOrden(Long idEncuesta, Integer orden);

    boolean existsByEncuestaIdAndOrdenAndIdNot(Long idEncuesta, Integer orden, Long id);
}
