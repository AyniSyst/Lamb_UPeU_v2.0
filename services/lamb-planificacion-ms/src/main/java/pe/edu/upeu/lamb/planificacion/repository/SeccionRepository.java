package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.EstadoSeccion;
import pe.edu.upeu.lamb.planificacion.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    @Query("SELECT s FROM Seccion s JOIN FETCH s.curso ORDER BY s.id")
    List<Seccion> findAllConCurso();

    List<Seccion> findByEstadoAndCursoCodigoIn(EstadoSeccion estado, Collection<String> codigos);
}
