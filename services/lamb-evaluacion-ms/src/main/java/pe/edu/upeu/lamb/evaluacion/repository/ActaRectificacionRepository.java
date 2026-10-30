package pe.edu.upeu.lamb.evaluacion.repository;

import pe.edu.upeu.lamb.evaluacion.entity.ActaRectificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActaRectificacionRepository extends JpaRepository<ActaRectificacion, Long> {

    List<ActaRectificacion> findByActaId(Long idActa);
}
