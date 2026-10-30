package pe.edu.upeu.lamb.trayectoria.repository;

import pe.edu.upeu.lamb.trayectoria.entity.HistorialCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialCursoRepository extends JpaRepository<HistorialCurso, Long> {
    boolean existsByIdPersonaAndIdGrupo(Long idPersona, Long idGrupo);

    List<HistorialCurso> findByIdPersonaOrderByPeriodoAscCodigoCursoAsc(Long idPersona);

    List<HistorialCurso> findByIdPersona(Long idPersona);

}
