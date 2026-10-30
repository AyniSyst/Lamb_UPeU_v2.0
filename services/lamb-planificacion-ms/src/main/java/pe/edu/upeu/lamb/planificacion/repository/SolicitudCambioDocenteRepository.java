package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioDocente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudCambioDocenteRepository extends JpaRepository<SolicitudCambioDocente, Long> {

    List<SolicitudCambioDocente> findByAsignacionId(Long idAsignacion);
}
