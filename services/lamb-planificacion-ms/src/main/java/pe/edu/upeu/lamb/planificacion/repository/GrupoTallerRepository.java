package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.GrupoTaller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoTallerRepository extends JpaRepository<GrupoTaller, Long> {

    List<GrupoTaller> findByPeriodoId(Long idPeriodo);

    boolean existsByPeriodoIdAndCodigo(Long idPeriodo, String codigo);

    boolean existsByPeriodoIdAndCodigoAndIdNot(Long idPeriodo, String codigo, Long id);
}
