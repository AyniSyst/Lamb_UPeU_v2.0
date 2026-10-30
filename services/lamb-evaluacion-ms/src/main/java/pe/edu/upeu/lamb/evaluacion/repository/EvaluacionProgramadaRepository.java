package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.EvaluacionProgramada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluacionProgramadaRepository extends JpaRepository<EvaluacionProgramada, Long> {

    List<EvaluacionProgramada> findByIdGrupo(Long idGrupo);

}
