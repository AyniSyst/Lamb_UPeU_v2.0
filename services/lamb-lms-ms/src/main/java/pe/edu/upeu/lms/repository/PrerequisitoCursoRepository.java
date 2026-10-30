package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upeu.lms.entity.PrerequisitoCurso;

import java.util.List;

public interface PrerequisitoCursoRepository extends JpaRepository<PrerequisitoCurso, Long> {

    @Query("SELECT p FROM PrerequisitoCurso p JOIN FETCH p.curso JOIN FETCH p.cursoPrerequisito")
    List<PrerequisitoCurso> findAllConCursos();
}
