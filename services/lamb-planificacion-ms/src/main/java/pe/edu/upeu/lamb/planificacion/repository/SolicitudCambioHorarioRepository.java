package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioHorario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudCambioHorarioRepository extends JpaRepository<SolicitudCambioHorario, Long> {

    List<SolicitudCambioHorario> findByHorarioId(Long idHorario);
}
