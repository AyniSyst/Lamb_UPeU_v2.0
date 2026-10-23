package pe.edu.upeu.lamb.trayectoria.repository;

import pe.edu.upeu.lamb.trayectoria.entity.HistorialCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialCursoRepository extends JpaRepository<HistorialCurso, Long> {
    boolean existsByIdPersonaAndIdSeccion(Long idPersona, Long idSeccion);

    List<HistorialCurso> findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(Long idPersona);
}
