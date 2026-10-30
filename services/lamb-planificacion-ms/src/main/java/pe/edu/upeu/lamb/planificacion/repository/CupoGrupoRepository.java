package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CupoGrupo;
import pe.edu.upeu.lamb.planificacion.entity.TipoCupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CupoGrupoRepository extends JpaRepository<CupoGrupo, Long> {

    List<CupoGrupo> findByGrupoId(Long idGrupo);

    boolean existsByGrupoIdAndTipo(Long idGrupo, TipoCupo tipo);

    boolean existsByGrupoIdAndTipoAndIdNot(Long idGrupo, TipoCupo tipo, Long id);

    @Query("SELECT COALESCE(SUM(c.cantidad), 0) FROM CupoGrupo c WHERE c.grupo.id = :idGrupo AND c.id <> :idExcluido")
    long sumarCantidadDelGrupo(@Param("idGrupo") Long idGrupo, @Param("idExcluido") Long idExcluido);
}
