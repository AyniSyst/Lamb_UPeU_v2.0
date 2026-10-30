package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.ResultadoAprendizaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoAprendizajeRepository extends JpaRepository<ResultadoAprendizaje, Long> {

    List<ResultadoAprendizaje> findByCursoId(Long idCurso);

    boolean existsByCursoIdAndCodigo(Long idCurso, String codigo);

    boolean existsByCursoIdAndCodigoAndIdNot(Long idCurso, String codigo, Long id);
}
