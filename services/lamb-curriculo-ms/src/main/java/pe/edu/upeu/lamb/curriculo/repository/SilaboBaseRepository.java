package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.SilaboBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SilaboBaseRepository extends JpaRepository<SilaboBase, Long> {

    List<SilaboBase> findByCursoId(Long idCurso);

    boolean existsByCursoIdAndVersion(Long idCurso, String version);

    boolean existsByCursoIdAndVersionAndIdNot(Long idCurso, String version, Long id);
}
