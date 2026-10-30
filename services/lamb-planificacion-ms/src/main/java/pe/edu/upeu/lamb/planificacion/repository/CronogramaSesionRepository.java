package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.CronogramaSesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CronogramaSesionRepository extends JpaRepository<CronogramaSesion, Long> {

    List<CronogramaSesion> findBySesionId(Long idSesion);
}
