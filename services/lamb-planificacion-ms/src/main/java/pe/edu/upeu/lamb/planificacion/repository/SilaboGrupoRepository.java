package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SilaboGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SilaboGrupoRepository extends JpaRepository<SilaboGrupo, Long> {

    List<SilaboGrupo> findByGrupoId(Long idGrupo);

    boolean existsByGrupoId(Long idGrupo);

    boolean existsByGrupoIdAndIdNot(Long idGrupo, Long id);
}
