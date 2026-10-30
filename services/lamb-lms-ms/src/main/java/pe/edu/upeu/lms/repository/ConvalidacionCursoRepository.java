package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.ConvalidacionCurso;

import java.util.List;

public interface ConvalidacionCursoRepository extends JpaRepository<ConvalidacionCurso, Long> {

    @Query("SELECT c FROM ConvalidacionCurso c JOIN FETCH c.curso")
    List<ConvalidacionCurso> findAllConCurso();
}
