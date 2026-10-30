package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.ParametroMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParametroMatriculaRepository extends JpaRepository<ParametroMatricula, Long> {

    List<ParametroMatricula> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndIdPrograma(Long idPeriodo, Long idPrograma);

    boolean existsByPeriodoIdAndIdProgramaAndIdNot(Long idPeriodo, Long idPrograma, Long id);
}
