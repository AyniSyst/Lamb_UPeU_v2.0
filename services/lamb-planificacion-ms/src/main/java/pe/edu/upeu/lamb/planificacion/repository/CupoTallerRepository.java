package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CupoTaller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CupoTallerRepository extends JpaRepository<CupoTaller, Long> {

    List<CupoTaller> findByGrupoTallerId(Long idGrupoTaller);

    boolean existsByGrupoTallerIdAndIdPrograma(Long idGrupoTaller, Long idPrograma);

    boolean existsByGrupoTallerIdAndIdProgramaAndIdNot(Long idGrupoTaller, Long idPrograma, Long id);

    @Query("SELECT COALESCE(SUM(c.cantidad), 0) FROM CupoTaller c WHERE c.grupoTaller.id = :idGrupoTaller AND c.id <> :idExcluido")
    long sumarCantidadDelTaller(@Param("idGrupoTaller") Long idGrupoTaller, @Param("idExcluido") Long idExcluido);
}
