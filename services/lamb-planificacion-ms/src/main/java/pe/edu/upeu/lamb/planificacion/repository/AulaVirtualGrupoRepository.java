package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.AulaVirtualGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AulaVirtualGrupoRepository extends JpaRepository<AulaVirtualGrupo, Long> {

    List<AulaVirtualGrupo> findByGrupoId(Long idGrupo);

    boolean existsByGrupoId(Long idGrupo);

    boolean existsByGrupoIdAndIdNot(Long idGrupo, Long id);
}
