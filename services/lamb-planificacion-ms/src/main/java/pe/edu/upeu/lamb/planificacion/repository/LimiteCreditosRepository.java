package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.LimiteCreditos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimiteCreditosRepository extends JpaRepository<LimiteCreditos, Long> {

    List<LimiteCreditos> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndIdProgramaAndCiclo(Long idPeriodo, Long idPrograma, Integer ciclo);

    boolean existsByPeriodoIdAndIdProgramaAndCicloAndIdNot(Long idPeriodo, Long idPrograma, Integer ciclo, Long id);
}
