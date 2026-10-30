package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.ConfiguracionEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ConfiguracionEvaluacionRepository extends JpaRepository<ConfiguracionEvaluacion, Long> {

    List<ConfiguracionEvaluacion> findByIdGrupo(Long idGrupo);

    boolean existsByIdGrupoAndComponenteId(Long idGrupo, Long idComponente);

    boolean existsByIdGrupoAndComponenteIdAndIdNot(Long idGrupo, Long idComponente, Long id);

    @Query("SELECT COALESCE(SUM(c.peso), 0) FROM ConfiguracionEvaluacion c WHERE c.idGrupo = :idGrupo AND c.id <> :idExcluido")
    BigDecimal sumarPesosDelGrupo(@Param("idGrupo") Long idGrupo, @Param("idExcluido") Long idExcluido);
}
