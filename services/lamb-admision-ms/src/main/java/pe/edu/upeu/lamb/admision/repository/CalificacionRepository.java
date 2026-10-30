package pe.edu.upeu.lamb.admision.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lamb.admision.entity.CalificacionExamen;

import java.util.List;
import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<CalificacionExamen, Long> {
    List<CalificacionExamen> findByExamenId(Long idExamen);
    Optional<CalificacionExamen> findByExamenIdAndPostulanteId(Long idExamen, Long idPostulante);
    boolean existsByExamenIdAndPostulanteId(Long idExamen, Long idPostulante);
    boolean existsByPostulanteId(Long idPostulante);
}
