package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.RecursoComplementario;

import java.util.List;

public interface RecursoComplementarioRepository extends JpaRepository<RecursoComplementario, Long> {

    @Query("SELECT r FROM RecursoComplementario r JOIN FETCH r.curso")
    List<RecursoComplementario> findAllConCurso();
}
