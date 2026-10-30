package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.RespuestaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta, Long> {

    List<RespuestaEncuesta> findByPreguntaId(Long idPregunta);

    List<RespuestaEncuesta> findByPreguntaIdAndIdDocente(Long idPregunta, Long idDocente);
}
