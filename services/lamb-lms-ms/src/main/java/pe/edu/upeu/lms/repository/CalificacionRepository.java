package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.Calificacion;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    @Query("SELECT c FROM Calificacion c JOIN FETCH c.evaluacion")
    List<Calificacion> findAllConEvaluacion();
}
