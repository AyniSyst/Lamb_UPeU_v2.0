package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudCierreFusionGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudCierreFusionGrupoRepository extends JpaRepository<SolicitudCierreFusionGrupo, Long> {

    List<SolicitudCierreFusionGrupo> findByGrupoId(Long idGrupo);
}
