package pe.edu.upeu.lamb.curriculo.repository;

import pe.edu.upeu.lamb.curriculo.entity.CursoExternoConvalidable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoExternoConvalidableRepository extends JpaRepository<CursoExternoConvalidable, Long> {

    List<CursoExternoConvalidable> findByTablaId(Long idTabla);

    boolean existsByTablaIdAndCodigoExterno(Long idTabla, String codigoExterno);

    boolean existsByTablaIdAndCodigoExternoAndIdNot(Long idTabla, String codigoExterno, Long id);
}
