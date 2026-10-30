package pe.edu.upeu.lamb.trayectoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.trayectoria.entity.SesionTutoria;

import java.util.List;

public interface SesionTutoriaRepository extends JpaRepository<SesionTutoria, Long> {
    List<SesionTutoria> findByTutoriaIdOrderByFechaSesionAsc(Long idTutoria);
}
