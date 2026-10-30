package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.Evaluacion;

import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    @Query("SELECT e FROM Evaluacion e JOIN FETCH e.curso")
    List<Evaluacion> findAllConCurso();
}
