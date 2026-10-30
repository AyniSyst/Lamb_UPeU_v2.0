package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudGrupoEquivalente;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.planificacion.entity.EstadoSolicitud;

import java.util.List;

public interface SolicitudGrupoEquivalenteRepository extends JpaRepository<SolicitudGrupoEquivalente, Long> {

    List<SolicitudGrupoEquivalente> findByGrupoHabilitadoId(Long idGrupoHabilitado);

    boolean existsByGrupoHabilitadoIdAndIdSolicitanteAndEstadoNot(Long idGrupoHabilitado, Long idSolicitante, EstadoSolicitud estado);
}
