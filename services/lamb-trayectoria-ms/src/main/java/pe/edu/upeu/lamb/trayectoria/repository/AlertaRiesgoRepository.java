package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.AlertaRiesgo;

import java.util.List;

public interface AlertaRiesgoRepository extends JpaRepository<AlertaRiesgo, Long> {
    List<AlertaRiesgo> findByIdPersonaOrderByFechaDeteccionDesc(Long idPersona);
    List<AlertaRiesgo> findByIdTrayectoriaOrderByFechaDeteccionDesc(Long idTrayectoria);
    List<AlertaRiesgo> findByEstado(String estado);
}
