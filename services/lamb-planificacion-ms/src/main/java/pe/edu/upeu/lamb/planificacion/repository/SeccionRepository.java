package pe.edu.upeu.lamb.planificacion.repository;

import pe.edu.upeu.lamb.planificacion.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    @Query("SELECT s FROM Seccion s JOIN FETCH s.curso")
    List<Seccion> findAllConCurso();
}
