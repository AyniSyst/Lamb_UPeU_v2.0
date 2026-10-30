package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.SolicitudCambioAula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudCambioAulaRepository extends JpaRepository<SolicitudCambioAula, Long> {

    List<SolicitudCambioAula> findByHorarioId(Long idHorario);
}
