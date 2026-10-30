package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.Pregunta;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    @Query("SELECT p FROM Pregunta p JOIN FETCH p.evaluacion")
    List<Pregunta> findAllConEvaluacion();
}
